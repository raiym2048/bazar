package com.example.bazar.service.impl;

import com.example.bazar.exception.CustomException;
import com.example.bazar.mapper.AuthMapper;
import com.example.bazar.model.domain.User;
import com.example.bazar.model.dto.auth.AuthResponse;
import com.example.bazar.model.dto.auth.LoginRequest;
import com.example.bazar.model.dto.auth.RegisterRequest;
import com.example.bazar.repository.UserRepository;
import com.example.bazar.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthMapper authMapper;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthResponse register(RegisterRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new CustomException("User with this email is already exist", HttpStatus.FOUND);
        }
        User user = userRepository.save(authMapper.toUserDto(request));
        return authMapper.toDto(user);
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        return authMapper.toDto(userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new CustomException("User with this email not found", HttpStatus.NOT_FOUND)));
    }
}
