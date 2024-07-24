package com.example.bazar.mapper.impl;

import com.example.bazar.config.JwtService;
import com.example.bazar.mapper.AuthMapper;
import com.example.bazar.model.domain.Seller;
import com.example.bazar.model.domain.User;
import com.example.bazar.model.dto.auth.AuthResponse;
import com.example.bazar.model.dto.auth.RegisterRequest;
import com.example.bazar.model.enums.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthMapperImpl implements AuthMapper {
    private final JwtService jwtService;
    private final PasswordEncoder encoder;
    @Override
    public AuthResponse toDto(User user) {
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(jwtService.generateToken(user));
        return authResponse;
    }

    @Override
    public User toUserDto(RegisterRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole(Role.CUSTOMER);
        return user;
    }

    @Override
    public Seller toSeller(User user) {
        Seller seller = new Seller();
       // todo
        return seller;
    }
}
