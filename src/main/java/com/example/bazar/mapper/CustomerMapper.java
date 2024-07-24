package com.example.bazar.mapper;


import com.example.bazar.model.domain.User;
import com.example.bazar.model.dto.customer.CustomerResponse;

import java.util.List;

public interface CustomerMapper {
    List<CustomerResponse> toDtoS(List<User> list);

    CustomerResponse toDto(User user);
}
