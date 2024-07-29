package com.example.bazar.controller;

import com.example.bazar.model.domain.Address;
import com.example.bazar.model.dto.auth.AuthResponse;
import com.example.bazar.model.dto.auth.SellerRegisterRequest;
import com.example.bazar.model.dto.manager.ManagerResponse;
import com.example.bazar.model.dto.seller.AddressRequest;
import com.example.bazar.service.ManagerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ManagerResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @GetMapping("/profile")
    public ManagerResponse getProfile(@RequestHeader("Authorization") String token) {
        return service.getProfile(token);
    }

    @PostMapping("/register/seller")
    public void registerSeller(@RequestBody SellerRegisterRequest request) {
         service.registerSeller(request);
    }

    @PostMapping("/add/address/types")
    public Address addAddressTypes(@RequestBody AddressRequest request){
        return service.addAddresses(request);
    }
}
