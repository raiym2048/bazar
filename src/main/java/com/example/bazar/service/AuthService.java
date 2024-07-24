package com.example.bazar.service;

import com.example.bazar.model.domain.User;
import com.example.bazar.model.dto.auth.AuthResponse;
import com.example.bazar.model.dto.auth.LoginRequest;
import com.example.bazar.model.dto.auth.ManualRegisterRequest;
import com.example.bazar.model.dto.auth.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest loginRequest);

    void manuelRegister(ManualRegisterRequest request);

    public User getUserFromToken(String token);

    String generateRandomPassword();
}
