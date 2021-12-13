package com.coffee.point.service;

import com.coffee.point.dto.user.AuthenticationRequestDTO;
import com.coffee.point.dto.user.RegistrationUserDTO;
import com.coffee.point.dto.user.UserDTO;
import com.coffee.point.exception.runtime.BadRequestException;
import com.coffee.point.exception.runtime.EntityNotFoundException;
import com.coffee.point.mapper.UserMapper;
import com.coffee.point.model.User;
import com.coffee.point.repository.UserRepository;
import com.coffee.point.security.jwt.JWTTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWTTokenProvider jwtTokenProvider;

    private final AuthenticationManager authenticationManager;

    public UserService(UserMapper userMapper, UserRepository userRepository, PasswordEncoder passwordEncoder,
                       JWTTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }


    /**
     * Users registration
     *
     * @param registrationUserDTO - dto with registration fields
     * @return - saved users DTO
     */
    public UserDTO userRegistration(RegistrationUserDTO registrationUserDTO) {

        User user = userMapper.map(registrationUserDTO, User.class);

        if (userRepository.getByLogin(registrationUserDTO.getLogin()).isPresent()) {
            throw new BadRequestException("Внимание! Пользователь с таким логином уже сущетсвует!");
        }

        if (registrationUserDTO.getPassword().equals(registrationUserDTO.getCheckPassword())) {
            user.setPassword(passwordEncoder.encode(registrationUserDTO.getPassword()));
        } else {
            throw new BadRequestException("Внимание! Пароли не совпадают!");
        }
        user.setRole(registrationUserDTO.getUserRole());
        user.setDeleted(false);

        return userMapper.map(userRepository.save(user), UserDTO.class);
    }


    /**
     * Users auth
     *
     * @param requestDto - DTP for auth
     * @return OK status with token
     */
    public ResponseEntity<Map<String, String>> authorization(@RequestBody AuthenticationRequestDTO requestDto) {
        try {
            String login = requestDto.getLogin();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, requestDto.getPassword()));

            User user = getByLogin(login);

            if (user == null) {
                throw new AuthenticationServiceException("NULL!");
            }

            String token = jwtTokenProvider.createToken(login, user.getRole());

            Map<String, String> response = new HashMap<>();
            response.put("login", login);
            response.put("token", token);

            return ResponseEntity.ok(response);

        } catch (AuthenticationServiceException e) {
            throw new AuthenticationServiceException("Invalid login!");
        }
    }


    /**
     * Поиск пользователя по логину
     *
     * @param login - users login
     * @return USER entity
     */
    private User getByLogin(String login) {
        return userRepository.getByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("Внимание! Невозможно найти login: " + login));
    }
}