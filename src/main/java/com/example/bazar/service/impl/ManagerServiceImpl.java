package com.example.bazar.service.impl;

import com.example.bazar.exception.CustomException;
import com.example.bazar.mapper.ManagerMapper;
import com.example.bazar.model.domain.User;
import com.example.bazar.model.dto.manager.ManagerResponse;
import com.example.bazar.repository.ManagerRepository;
import com.example.bazar.service.AuthService;
import com.example.bazar.service.ManagerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;
    private final AuthService authService;
    @Override
    public List<ManagerResponse> all(int offset, int pageSize) {
        return managerMapper.toResponseList(managerRepository.findAll(PageRequest.of(offset, pageSize)).stream().toList());
    }

    @Override
    public ManagerResponse getById(UUID id) {
        return managerMapper.toResponse(managerRepository.findById(id).orElseThrow(() -> new CustomException("Manager not found", HttpStatus.NOT_FOUND)));
    }

    @Override
    public ManagerResponse getProfile(String token) {
        User user = authService.getUserFromToken(token);
        return managerMapper.toResponse(managerRepository.findById(user.getId()).orElseThrow(() -> new CustomException("Manager not found", HttpStatus.NOT_FOUND)));
    }
}
