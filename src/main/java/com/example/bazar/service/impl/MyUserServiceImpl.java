package com.example.bazar.service.impl;

import com.example.bazar.exception.CustomException;
import com.example.bazar.mapper.UserMapper;
import com.example.bazar.model.domain.Seller;
import com.example.bazar.model.domain.User;
import com.example.bazar.model.dto.user.UserRequest;
import com.example.bazar.model.enums.Role;
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

}
