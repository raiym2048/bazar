package com.example.bazar.controller;

import com.example.bazar.model.domain.Product;
import com.example.bazar.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController("/seller")
@RequiredArgsConstructor
public class SellerController {
    private final SellerService sellerService;
//    @PostMapping("/addProduct")
//    public void addProduct(@RequestBody Product product, @RequestHeader("Authorization") String token){
//        sellerService.addProduct(product, token);
//    }
//    @PostMapping("/removeProduct/{id}")
//    public void removeProduct(@PathVariable Long id, @RequestHeader("Authorization") String token){
//        sellerService.removeProduct(id, token);
//    }
//    @PostMapping("/updateProduct/{productId}")
//    public void updateProduct(@PathVariable Long productId, @RequestBody Product product, @RequestHeader("Authorization") String token){
//        sellerService.updateProduct(productId, product, token);
//    }
}
