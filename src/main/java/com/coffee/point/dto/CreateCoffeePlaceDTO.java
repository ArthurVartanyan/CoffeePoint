package com.coffee.point.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateCoffeePlaceDTO {

    private String placeName;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String aboutPlace;
}