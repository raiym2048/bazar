package com.example.bazar.service.impl;

import com.example.bazar.exception.CustomException;
import com.example.bazar.mapper.AuthMapper;
import com.example.bazar.mapper.ManagerMapper;
import com.example.bazar.model.domain.Seller;
import com.example.bazar.model.domain.User;
import com.example.bazar.model.dto.auth.AuthResponse;
import com.example.bazar.model.dto.auth.SellerRegisterRequest;
import com.example.bazar.model.dto.manager.ManagerResponse;
import com.example.bazar.model.enums.Role;
import com.example.bazar.repository.ManagerRepository;
import com.example.bazar.repository.SellerRepository;
import com.example.bazar.repository.UserRepository;
import com.example.bazar.service.AuthService;
import com.example.bazar.service.ManagerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;
    private final AuthService authService;
    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;
    private final AuthMapper authMapper;
    private final PasswordEncoder encoder;

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

    @Override
    public AuthResponse registerSeller(SellerRegisterRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if (userOptional.isPresent()) {
            throw new CustomException("User already exists", HttpStatus.CONFLICT);
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setRole(Role.SELLER);
        String randPassword = authService.generateRandomPassword();
        user.setPassword(encoder.encode(randPassword));
        System.out.println("Password of the seller: " +  randPassword);

        Seller seller = new Seller();
        sellerRepository.save(seller);
        user.setSeller(seller);
        userRepository.save(user);
        //todo send email and send password: randPassword

        return authMapper.toDto(user);
    }
}
