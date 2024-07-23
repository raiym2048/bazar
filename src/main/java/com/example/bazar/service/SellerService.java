package com.example.bazar.service;

import com.example.bazar.model.domain.Product;
import com.example.bazar.model.dto.seller.SellerRequest;
import com.example.bazar.model.dto.seller.SellerResponse;

import java.util.List;
import java.util.UUID;

public interface SellerService {
    List<SellerResponse> all(int offset, int pageSize);
    SellerResponse getById(UUID id);
    SellerResponse getProfile(String token);
    SellerResponse update(String token, SellerRequest request);
    void delete(String token);
}
