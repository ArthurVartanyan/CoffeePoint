package com.coffee.point.dto.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldErrorDTO {

    private String field;

    private String message;
}