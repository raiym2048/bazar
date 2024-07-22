package com.example.bazar.mapper.impl;

import com.example.bazar.mapper.CustomerMapper;
import com.example.bazar.model.domain.Customer;
import com.example.bazar.model.dto.customer.CustomerResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerMapperImpl implements CustomerMapper {
    @Override
    public CustomerResponse toResponse(Customer customer) {
        CustomerResponse response = new CustomerResponse();
        response.setId(customer.getId());
        response.setName(customer.getName());
        response.setEmail(customer.getEmail());
        return response;
    }

    @Override
    public List<CustomerResponse> toResponseList(List<Customer> customers) {
        List<CustomerResponse> responses = new ArrayList<>();
        for (Customer customer : customers) {
            responses.add(toResponse(customer));
        }
        return responses;
    }
}
