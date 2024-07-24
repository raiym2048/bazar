package com.example.bazar.service;

import com.example.bazar.model.dto.user.UserResponse;

import java.util.List;
import java.util.UUID;

public interface MyUserService {
    List<UserResponse> all(int offset, int pageSize);

    UserResponse getById(UUID id);
}
