package com.coffee.point.exception.runtime;

import javax.naming.AuthenticationException;

public class JWTAuthenticationException extends AuthenticationException {

    public JWTAuthenticationException(String message) {
        super(message);
    }
}