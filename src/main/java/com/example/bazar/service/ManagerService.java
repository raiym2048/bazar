package com.example.bazar.service;

import com.example.bazar.model.dto.auth.AuthResponse;
import com.example.bazar.model.dto.auth.SellerRegisterRequest;
import com.example.bazar.model.dto.manager.ManagerResponse;

import java.util.List;
import java.util.UUID;

public interface ManagerService {
    List<ManagerResponse> all(int offset, int pageSize);

    ManagerResponse getById(UUID id);

    ManagerResponse getProfile(String token);

    AuthResponse registerSeller(SellerRegisterRequest request);
}
