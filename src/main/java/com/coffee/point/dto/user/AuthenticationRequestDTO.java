package com.coffee.point.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequestDTO {

    private String login;

    private String password;
}