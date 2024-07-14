package com.example.bazar.controller;

import com.example.bazar.model.dto.auth.AuthResponse;
import com.example.bazar.model.dto.auth.LoginRequest;
import com.example.bazar.model.dto.auth.RegisterRequest;
import com.example.bazar.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService service;

    @PostMapping("/register")
    public AuthResponse register(RegisterRequest request) {
        return service.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(LoginRequest loginRequest) {
        return service.login(loginRequest);
    }
}
