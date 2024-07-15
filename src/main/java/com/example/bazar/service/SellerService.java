package com.example.bazar.service;

import com.example.bazar.model.domain.Product;

public interface SellerService {
    void addProduct(Product product, String token);
    void removeProduct(Long productId, String token);
    void updateProduct(Long productId, Product product, String token);


}
