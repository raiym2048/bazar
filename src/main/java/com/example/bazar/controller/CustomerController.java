package com.example.bazar.controller;

import com.example.bazar.model.dto.customer.CustomerResponse;
import com.example.bazar.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService service;

    @GetMapping("/all")
    public List<CustomerResponse> all(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.all(offset, pageSize);
    }

    @GetMapping("/{id}")
    public CustomerResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/profile")
    public CustomerResponse getProfile(@RequestHeader("Authorization") String token) {
        return service.getProfile(token);
    }
}
