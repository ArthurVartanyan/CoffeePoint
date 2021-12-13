package com.coffee.point.mapper;

import com.coffee.point.dto.user.RegistrationUserDTO;
import com.coffee.point.dto.user.UserDTO;
import com.coffee.point.model.User;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory mapperFactory) {

        mapperFactory.classMap(RegistrationUserDTO.class, User.class).byDefault().register();

        mapperFactory.classMap(User.class, UserDTO.class).byDefault().register();
    }
}