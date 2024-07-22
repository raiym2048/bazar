package com.example.bazar.model.dto.customer;

import lombok.Data;

@Data
public class CustomerResponse {
    private Long id;
    private String name;
    private String email;
}
