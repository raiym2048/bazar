package com.example.bazar.service.impl;

import com.example.bazar.config.JwtService;
import com.example.bazar.exception.CustomException;
import com.example.bazar.mapper.AuthMapper;
import com.example.bazar.model.domain.User;
import com.example.bazar.model.dto.auth.AuthResponse;
import com.example.bazar.model.dto.auth.LoginRequest;
import com.example.bazar.model.dto.auth.ManualRegisterRequest;
import com.example.bazar.model.dto.auth.RegisterRequest;
import com.example.bazar.model.enums.Role;
import com.example.bazar.repository.ManagerRepository;
import com.example.bazar.repository.SellerRepository;
import com.example.bazar.repository.UserRepository;
import com.example.bazar.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;
    private final ManagerRepository managerRepository;
    private final AuthMapper authMapper;
    private final AuthenticationManager authenticationManager;
    private final JavaMailSender mailSender;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new CustomException("User with this email is already exist", HttpStatus.FOUND);
        }
        User user = authMapper.toUserDto(request);

        userRepository.save(user);
        return authMapper.toDto(user);
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
        }catch (Exception e){
            throw new CustomException("Invalid email or password", HttpStatus.BAD_REQUEST);
        }

        return authMapper.toDto(userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new CustomException("User with this email not found", HttpStatus.NOT_FOUND)));
    }

    @Override
    public void manuelRegister(ManualRegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new CustomException("User with this email is already exist", HttpStatus.FOUND);
        }

        Role role = Role.valueOf(request.getRole());
        String encodedPassword = encoder.encode(sendPassword(request.getEmail()));

/*        switch (role) {
            case SELLER:
                Seller seller = new Seller(request.getName(), request.getEmail(), encodedPassword);
                sellerRepository.save(seller);
                break;
            case MANAGER:
                Manager manager = new Manager(request.getName(), request.getEmail(), encodedPassword);
                managerRepository.save(manager);
                break;
            default:
                User user = new User(request.getName(), request.getEmail(), encodedPassword, role);
                userRepository.save(user);
                break;
        }*/
    }

    @Override
    public User getUserFromToken(String token) {
        token = token.substring(7);
        String userEmail = jwtService.getUserEmail(token);
        return userRepository.findByEmail(userEmail).orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));
    }

    private String sendPassword(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ecomarket1111@gmail.com");
        message.setTo(email);
        message.setSubject("Registration to Bazar");
        String password = generateRandomPassword();
        message.setText("Email: " + email +
                "\nPassword: " + password);
        try {
            mailSender.send(message);
        } catch (Exception e) {
            log.info("Error during sending message: {}", e.getMessage());  // TODO: Fix the email authentication!!!
        }
        System.out.println("This is a password: " + password);
        return password;
    }

    @Override
    public String generateRandomPassword() {
        String elements = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789/?!<>[]{}";
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        int size = random.nextInt(20) + 4;
        for (int i = 0; i < size; i++) {
            int index = random.nextInt(elements.length());
            password.append(elements.charAt(index));
        }
        return password.toString();
    }
}
