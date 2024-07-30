package com.example.bazar.model.dto.auth;

import com.example.bazar.model.domain.Contact;
import com.example.bazar.model.dto.seller.AddressRequest;
import com.example.bazar.model.dto.seller.ContactRequest;
import com.example.bazar.model.enums.Role;
import lombok.Data;

import java.util.List;

@Data
public class SellerRegisterRequest {
    private String companyName;
    private List<AddressRequest> addressRequest;
    private ContactRequest contact;
}
