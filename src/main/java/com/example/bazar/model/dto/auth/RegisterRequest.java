package com.example.bazar.model.dto.auth;

import com.example.bazar.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @Size(min = 4, max = 24, message = "Size: min = 4, max = 24")
    private String name;
    @Email(message = "Error email")
    private String email;
    @Size(min = 4, max = 24, message = "Size: min = 4, max = 24")
    private String password;

}
