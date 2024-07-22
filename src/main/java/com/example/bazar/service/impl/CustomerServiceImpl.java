package com.example.bazar.service.impl;

import com.example.bazar.exception.CustomException;
import com.example.bazar.mapper.CustomerMapper;
import com.example.bazar.model.domain.User;
import com.example.bazar.model.dto.customer.CustomerResponse;
import com.example.bazar.repository.CustomerRepository;
import com.example.bazar.service.AuthService;
import com.example.bazar.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper customerMapper;
    private final AuthService authService;
    @Override
    public List<CustomerResponse> all(int offset, int pageSize) {
        return customerMapper.toResponseList(repository.findAll(PageRequest.of(offset, pageSize)).stream().toList());
    }

    @Override
    public CustomerResponse findById(Long id) {
        return customerMapper.toResponse(repository.findById(id).orElseThrow(() -> new CustomException("Customer not found", HttpStatus.NOT_FOUND)));
    }

    @Override
    public CustomerResponse getProfile(String token) {
        User user = authService.getUserFromToken(token);
        return customerMapper.toResponse(repository.findById(user.getId()).orElseThrow(() -> new CustomException("Customer not found", HttpStatus.NOT_FOUND)));
    }
}
