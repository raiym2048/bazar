package com.example.bazar.controller;

import com.example.bazar.model.domain.Product;
import com.example.bazar.model.dto.seller.SellerRequest;
import com.example.bazar.model.dto.seller.SellerResponse;
import com.example.bazar.service.SellerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/sellers")
public class SellerController {
    private final SellerService sellerService;

    @GetMapping
    public List<SellerResponse> all(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int pageSize) {
        return sellerService.all(offset, pageSize);
    }

    @GetMapping("/{id}")
    public SellerResponse getById(@PathVariable UUID id) {
        return sellerService.getById(id);
    }

    @GetMapping("/profile")
    public SellerResponse getProfile(@RequestHeader("Authorization") String token) {
        return sellerService.getProfile(token);
    }

    @PutMapping("/update")
    public SellerResponse update(@RequestHeader("Authorization") String token, @RequestBody SellerRequest request) {
        return sellerService.update(token, request);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestHeader("Authorization") String token) {
        sellerService.delete(token);
    }
}
