package com.example.bazar.service.impl;

import com.example.bazar.model.domain.Product;
import com.example.bazar.model.domain.Seller;
import com.example.bazar.model.domain.User;
import com.example.bazar.repository.ProductRepository;
import com.example.bazar.repository.SellerRepository;
import com.example.bazar.repository.UserRepository;
import com.example.bazar.service.AuthService;
import com.example.bazar.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final AuthService authService;


//    @Override
//    public void addProduct(UUID product, String token) {
//        User user = authService.getUserFromToken(token);
//        Seller seller = sellerRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("Seller not found"));
//        seller.getProducts().add(product);
//        userRepository.save(seller);
//        productRepository.save(product);
//    }
//
//    @Override
//    public void removeProduct(UUID productId, String token) {
//        User user = authService.getUserFromToken(token);
//        Seller seller = sellerRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("Seller not found"));
//        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
//        seller.getProducts().remove(product);
//        userRepository.save(seller);
//        productRepository.delete(product);
//    }
//
//    @Override
//    public void updateProduct(UUID productId, Product product, String token) {
//        User user = authService.getUserFromToken(token);
//        Seller seller = sellerRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("Seller not found"));
//        Product oldProduct = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
//        oldProduct.setName(product.getName());
//        oldProduct.setPrice(product.getPrice());
//        oldProduct.setDescription(product.getDescription());
//        productRepository.save(oldProduct);
//    }
}
