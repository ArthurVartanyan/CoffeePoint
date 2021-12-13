package com.coffee.point.dto.user;

import com.coffee.point.model.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationUserDTO {

    private String firstName;

    private String lastName;

    private String middleName;

    private String login;

    private String password;

    private String checkPassword;

    private String mail;

    private UserRole userRole;
}