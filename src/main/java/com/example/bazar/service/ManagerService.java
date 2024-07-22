package com.example.bazar.service;

import com.example.bazar.model.dto.manager.ManagerResponse;

import java.util.List;

public interface ManagerService {
    List<ManagerResponse> all(int offset, int pageSize);
    ManagerResponse getById(Long id);
    ManagerResponse getProfile(String token);
}
