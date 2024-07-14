package com.example.bazar.model.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    @Email(message = "Error email")
    private String email;
    @Size(min = 4, max = 24, message = "Size: min = 4, max = 24")
    private String password;
}
