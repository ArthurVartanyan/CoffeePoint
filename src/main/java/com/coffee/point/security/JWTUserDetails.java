package com.coffee.point.security;

import com.coffee.point.model.User;
import com.coffee.point.repository.UserRepository;
import com.coffee.point.security.jwt.JWTUserFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class JWTUserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    public JWTUserDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        User user = userRepository.getByLogin(username)
                .orElseThrow(() -> new AuthenticationServiceException("Пользователя с такими данными не существует!"));

        return JWTUserFactory.create(user);
    }
}