package com.coffee.point.dto.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ErrorDTO {

    private String error;

    private List<FieldErrorDTO> fieldErrors;
}