package com.zensar.login.mapper;

import com.zensar.login.dto.UserDto;
import com.zensar.login.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface UserMapper {

    User toUser(UserDto userDto);

    UserDto toUserDto(User user);
}
