package com.coffee.point.mapper;

import com.coffee.point.dto.CoffeePlaceDTO;
import com.coffee.point.dto.CreateCoffeePlaceDTO;
import com.coffee.point.model.CoffeePlace;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class CoffeePlaceMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(CoffeePlace.class, CreateCoffeePlaceDTO.class).byDefault().register();

        mapperFactory.classMap(CoffeePlace.class, CoffeePlaceDTO.class).byDefault().register();

        mapperFactory.classMap(CreateCoffeePlaceDTO.class, CoffeePlace.class).byDefault().register();
    }
}