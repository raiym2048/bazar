package com.example.bazar.model.dto.auth;

import com.example.bazar.model.enums.Role;
import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private Role role;
}
