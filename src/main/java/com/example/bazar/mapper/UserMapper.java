package com.example.bazar.mapper;

import com.example.bazar.model.domain.User;
import com.example.bazar.model.dto.user.UserRequest;

public interface UserMapper {
    User toUserDto(UserRequest request);
}
