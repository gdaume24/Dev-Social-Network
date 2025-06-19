package com.network.mapper;

import org.mapstruct.Mapper;

import com.network.dto.UserDto;
import com.network.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {
}
