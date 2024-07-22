package com.example.bazar.service;

import com.example.bazar.model.dto.manager.ManagerResponse;

import java.util.List;
import java.util.UUID;

public interface ManagerService {
    List<ManagerResponse> all(int offset, int pageSize);
    ManagerResponse getById(UUID id);
    ManagerResponse getProfile(String token);
}
