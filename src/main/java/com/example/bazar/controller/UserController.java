package com.example.bazar.controller;

import com.example.bazar.model.dto.user.UserRequest;
import com.example.bazar.service.MyUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final MyUserService service;

    @PostMapping("/register")
    public void register(UserRequest request) {
        service.register(request);
    }
}
