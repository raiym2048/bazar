package com.example.bazar.mapper.impl;

import com.example.bazar.mapper.UserMapper;
import com.example.bazar.model.domain.User;
import com.example.bazar.model.dto.user.UserRequest;
import com.example.bazar.model.dto.user.UserResponse;
import com.example.bazar.model.enums.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setRole(String.valueOf(user.getRole()));
        return response;
    }

    @Override
    public List<UserResponse> toResponseList(List<User> users) {
        List<UserResponse> responses = new ArrayList<>();
        for (User user : users) {
            responses.add(toResponse(user));
        }
        return responses;
    }

    @Override
    public User toUserDto(UserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setRole(Role.valueOf(request.getRole()));
        return user;
    }
}
