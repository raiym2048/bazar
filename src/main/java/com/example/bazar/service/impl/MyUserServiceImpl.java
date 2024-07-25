package com.example.bazar.service.impl;

import com.example.bazar.exception.CustomException;
import com.example.bazar.mapper.CustomerMapper;
import com.example.bazar.mapper.ManagerMapper;
import com.example.bazar.mapper.SellerMapper;
import com.example.bazar.mapper.UserMapper;
import com.example.bazar.model.domain.User;
import com.example.bazar.model.dto.user.UserResponse;
import com.example.bazar.model.enums.Role;
import com.example.bazar.repository.ManagerRepository;
import com.example.bazar.repository.SellerRepository;
import com.example.bazar.repository.UserRepository;
import com.example.bazar.service.MyUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class MyUserServiceImpl implements MyUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;
    private final SellerRepository sellerRepository;
    private final SellerMapper sellerMapper;
    private final CustomerMapper customerMapper;

    @Override
    public List<UserResponse> all(int offset, int pageSize) {
        return userMapper.toResponseList(userRepository.findAll(PageRequest.of(offset, pageSize)).stream().toList());
    }

    @Override
    public ResponseEntity<?> getById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));
        if (user.getRole() == Role.MANAGER) {
            return new ResponseEntity<>(managerMapper.toResponse(managerRepository.findById(user.getId()).orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND))), HttpStatus.OK);
        } else if (user.getRole() == Role.SELLER) {
            return new ResponseEntity<>(sellerMapper.toResponse(sellerRepository.findById(user.getId()).orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND))), HttpStatus.OK);
        } else if (user.getRole() == Role.CUSTOMER) {
            // TODO: Return customers:
        } else {
            return new ResponseEntity<>(userMapper.toResponse(user), HttpStatus.OK);
        }
        return null;
    }
}
