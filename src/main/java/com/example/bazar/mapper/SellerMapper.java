package com.example.bazar.mapper;

import com.example.bazar.model.domain.Seller;
import com.example.bazar.model.dto.seller.SellerRequest;
import com.example.bazar.model.dto.seller.SellerResponse;

import java.util.List;

public interface SellerMapper {
    SellerResponse toResponse(Seller seller);

    List<SellerResponse> toResponseList(List<Seller> sellers);

    Seller toSeller(Seller seller, SellerRequest request);
}
