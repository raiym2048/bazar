package com.example.bazar.mapper.impl;

import com.example.bazar.mapper.UserMapper;
import com.example.bazar.model.domain.User;
import com.example.bazar.model.dto.user.UserRequest;
import com.example.bazar.model.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public User toUserDto(UserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setRole(Role.valueOf(request.getRole()));
        return user;
    }
}
