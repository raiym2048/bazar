package com.example.bazar.model.dto.user;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String email;
    private String role;
}
