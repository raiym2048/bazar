package com.example.bazar.mapper;

import com.example.bazar.model.domain.User;
import com.example.bazar.model.dto.user.UserRequest;
import com.example.bazar.model.dto.user.UserResponse;

import java.util.List;

public interface UserMapper {
    UserResponse toResponse(User user);
    List<UserResponse> toResponseList(List<User> users);
    User toUserDto(UserRequest request);
}
