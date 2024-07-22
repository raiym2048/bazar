package com.example.bazar.service;

import com.example.bazar.model.dto.user.UserRequest;
import com.example.bazar.model.dto.user.UserResponse;

import java.util.List;

public interface MyUserService {
    List<UserResponse> all(int offset, int pageSize);
    UserResponse getById(Long id);
}
