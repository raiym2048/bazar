package com.example.bazar.service;

import com.example.bazar.model.dto.customer.CustomerResponse;

import java.util.List;

public interface CustomerService {
    List<CustomerResponse> all(int offset, int pageSize);
    CustomerResponse findById(Long id);
    CustomerResponse getProfile(String token);
}
