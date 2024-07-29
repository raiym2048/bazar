package com.example.bazar.model.dto.seller;

import lombok.Data;

import java.util.UUID;

@Data
public class SellerResponse {
    private UUID id;
    private String address;
    private String phoneNumber;
    private String companyName;
    private String image;
}
