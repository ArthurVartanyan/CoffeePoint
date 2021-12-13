package com.coffee.point.controller;

import com.coffee.point.dto.user.AuthenticationRequestDTO;
import com.coffee.point.dto.user.RegistrationUserDTO;
import com.coffee.point.dto.user.UserDTO;
import com.coffee.point.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/registration")
    public ResponseEntity<UserDTO> userRegistration(@RequestBody RegistrationUserDTO registrationUserDTO) {
        return new ResponseEntity<>(userService.userRegistration(registrationUserDTO), HttpStatus.OK);
    }

    @PostMapping("/api/authentication")
    public ResponseEntity<Map<String, String>> authorization(@RequestBody AuthenticationRequestDTO requestDTO) {
        return userService.authorization(requestDTO);
    }
}