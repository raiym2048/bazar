package com.example.bazar.service.impl;

import com.example.bazar.exception.CustomException;
import com.example.bazar.mapper.UserMapper;
import com.example.bazar.model.domain.User;
import com.example.bazar.model.dto.user.UserRequest;
import com.example.bazar.repository.UserRepository;
import com.example.bazar.service.MyUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
@Slf4j
public class MyUserServiceImpl implements MyUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Autowired
    private JavaMailSender mailSender;
    private final PasswordEncoder encoder;
    @Override
    public void register(UserRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new CustomException("User with this email is already exist", HttpStatus.FOUND);
        }
        User user = userMapper.toUserDto(request);
        String password = sendPassword(user.getEmail());
        user.setPassword(encoder.encode(password));
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
        return password;
    }

    private String generateRandomPassword() {
        String elements = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789/?!<>[]{}";
        String password = null;
        Random random = new Random();
        int size = random.nextInt(20) + 4;
        for (int i = 0; i < size; i++) {
            int index = random.nextInt(elements.length());
            password = password + elements.charAt(index);
        }
        return password;
    }
}
