package com.example.bazar.model.dto.product;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductRequest {
    @Size(min = 2, max = 24, message = "Size: min = 2, max = 24")
    private String name;
    private String type;
    private double price;
    @Lob
    private String description;
}
