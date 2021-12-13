package com.coffee.point.security.jwt;

import com.coffee.point.model.User;

public class JWTUserFactory {

    public JWTUserFactory() {
    }

    public static JWTUser create(User user) {
        return new JWTUser(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getMiddleName(),
                user.getLogin(),
                user.getPassword(),
                user.isDeleted(),
                user.getRole()
        );
    }
}