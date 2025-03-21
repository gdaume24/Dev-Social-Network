package com.network.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.network.dto.UserDto;
import com.network.models.User;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {
}
