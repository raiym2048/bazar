package com.example.bazar.service.impl;

import com.example.bazar.exception.CustomException;
import com.example.bazar.mapper.UserMapper;
import com.example.bazar.model.dto.user.UserResponse;
import com.example.bazar.repository.UserRepository;
import com.example.bazar.service.MyUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class MyUserServiceImpl implements MyUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponse> all(int offset, int pageSize) {
        return userMapper.toResponseList(userRepository.findAll(PageRequest.of(offset, pageSize)).stream().toList());
    }

    @Override
    public UserResponse getById(UUID id) {
        return userMapper.toResponse(userRepository.findById(id).orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND)));
    }
}
