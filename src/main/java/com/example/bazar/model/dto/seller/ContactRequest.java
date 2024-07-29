package com.example.bazar.model.dto.seller;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ContactRequest {
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    private String additionalPhoneNumber;
}
