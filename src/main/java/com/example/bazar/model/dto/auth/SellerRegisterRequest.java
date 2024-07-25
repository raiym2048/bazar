package com.example.bazar.model.dto.auth;

import com.example.bazar.model.enums.Role;
import lombok.Data;

@Data
public class SellerRegisterRequest {
    private String email;
}
