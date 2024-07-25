package com.example.bazar.controller;

import com.example.bazar.model.dto.user.UserResponse;
import com.example.bazar.service.MyUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final MyUserService service;

    @GetMapping
    public List<UserResponse> all(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.all(offset, pageSize);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }
}
