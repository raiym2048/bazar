package com.example.bazar.model.dto.seller;

import lombok.Data;

@Data
public class SellerRequest {
    private String name;
    private String email;
    private String phoneNumber;
    private String companyName;
}
