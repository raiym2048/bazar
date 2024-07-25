package com.example.bazar.mapper;

import com.example.bazar.model.domain.Seller;
import com.example.bazar.model.domain.User;
import com.example.bazar.model.dto.auth.AuthResponse;
import com.example.bazar.model.dto.auth.RegisterRequest;

public interface AuthMapper {
    AuthResponse toDto(User user);

    User toUserDto(RegisterRequest request);

    Seller toSeller(User user);
}
