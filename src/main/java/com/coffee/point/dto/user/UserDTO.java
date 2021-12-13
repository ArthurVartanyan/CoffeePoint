package com.coffee.point.dto.user;

import com.coffee.point.model.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String middleName;

    private String login;

    private UserRole role;
}