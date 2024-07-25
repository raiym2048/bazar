package com.example.bazar.service;

import com.example.bazar.model.dto.user.UserRequest;
import com.example.bazar.model.dto.user.UserResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface MyUserService {
    List<UserResponse> all(int offset, int pageSize);
    ResponseEntity<?> getById(UUID id);
}
