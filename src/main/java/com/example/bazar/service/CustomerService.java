package com.example.bazar.service;

import com.example.bazar.model.dto.customer.CustomerResponse;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<CustomerResponse> all(int offset, int pageSize);

    CustomerResponse findById(UUID id);

    CustomerResponse getProfile(String token);
}
