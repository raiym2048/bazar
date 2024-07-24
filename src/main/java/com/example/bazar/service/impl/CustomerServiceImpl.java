package com.example.bazar.service.impl;

import com.example.bazar.exception.CustomException;
import com.example.bazar.mapper.CustomerMapper;
import com.example.bazar.model.domain.User;
import com.example.bazar.model.dto.customer.CustomerResponse;
import com.example.bazar.model.enums.Role;
import com.example.bazar.repository.UserRepository;
import com.example.bazar.service.AuthService;
import com.example.bazar.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CustomerMapper customerMapper;
    @Override
    public List<CustomerResponse> all(int offset, int pageSize) {
        return customerMapper.toDtoS(userRepository.findAllByRole(PageRequest.of(offset, pageSize), Role.CUSTOMER).stream().toList());
    }

    @Override
    public CustomerResponse findById(UUID id) {
        return customerMapper.toDto(userRepository.findById(id).orElseThrow(() -> new CustomException("Customer not found", HttpStatus.NOT_FOUND)));
    }

    @Override
    public CustomerResponse getProfile(String token) {
        User user = authService.getUserFromToken(token);
        return customerMapper.toDto(userRepository.findById(user.getId()).orElseThrow(() -> new CustomException("Customer not found", HttpStatus.NOT_FOUND)));
    }
}
