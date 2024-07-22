package com.example.bazar.mapper;

import com.example.bazar.model.domain.Customer;
import com.example.bazar.model.dto.customer.CustomerResponse;

import java.util.List;

public interface CustomerMapper {
    CustomerResponse toResponse(Customer customer);
    List<CustomerResponse> toResponseList(List<Customer> customers);
}
