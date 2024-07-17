package com.example.bazar.model.dto.product;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class ProductRequest {
    @Lob
    private String description;
    @Email
    private String sellerEmail;
}
