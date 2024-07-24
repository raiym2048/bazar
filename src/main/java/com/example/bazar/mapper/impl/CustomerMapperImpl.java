package com.example.bazar.mapper.impl;


import com.example.bazar.mapper.CustomerMapper;
import com.example.bazar.model.domain.User;
import com.example.bazar.model.dto.customer.CustomerResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerMapperImpl implements CustomerMapper {
    @Override
    public List<CustomerResponse> toDtoS(List<User> list) {
        List<CustomerResponse> responses = new ArrayList<>();
        for (User user : list) {
            responses.add(toDto(user));
        }
        return responses;
    }

    @Override
    public CustomerResponse toDto(User user) {
        CustomerResponse response = new CustomerResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        return response;
    }
}
