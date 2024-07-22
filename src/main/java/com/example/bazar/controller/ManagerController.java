package com.example.bazar.controller;

import com.example.bazar.model.dto.manager.ManagerResponse;
import com.example.bazar.service.ManagerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/manager")
public class ManagerController {
    private final ManagerService service;

    @GetMapping("/all")
    public List<ManagerResponse> all(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.all(offset, pageSize);
    }

    @GetMapping("/{id}")
    public ManagerResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/profile")
    public ManagerResponse getProfile(@RequestHeader("Authorization") String token) {
        return service.getProfile(token);
    }
}
