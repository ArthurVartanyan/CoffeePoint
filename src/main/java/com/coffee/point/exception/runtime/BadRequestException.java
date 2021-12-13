package com.coffee.point.exception.runtime;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}