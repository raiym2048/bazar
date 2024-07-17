package com.example.bazar.controller;

import com.example.bazar.model.domain.Product;
import com.example.bazar.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController("/seller")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;


    @PostMapping("/addProduct")
    public void addProduct(@RequestBody Product product, @RequestHeader("Authorization") String token){
        sellerService.addProduct(product, token);
    }

    @PostMapping("/removeProduct")
    public void removeProduct(@RequestBody Long productId, @RequestHeader("Authorization") String token){
        sellerService.removeProduct(productId, token);
    }

    @PostMapping("/updateProduct")
    public void updateProduct(@RequestBody Long productId, @RequestBody Product product, @RequestHeader("Authorization") String token){
        sellerService.updateProduct(productId, product, token);
    }
}
