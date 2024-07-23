package com.example.bazar.mapper.impl;

import com.example.bazar.mapper.SellerMapper;
import com.example.bazar.model.domain.Seller;
import com.example.bazar.model.dto.seller.SellerRequest;
import com.example.bazar.model.dto.seller.SellerResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SellerMapperImpl implements SellerMapper {
    @Override
    public SellerResponse toResponse(Seller seller) {
        SellerResponse response = new SellerResponse();
        response.setId(seller.getId());
        response.setName(seller.getName());
        response.setEmail(seller.getEmail());
        response.setAddress(seller.getAddress());
        response.setPhoneNumber(seller.getPhoneNumber());
        response.setCompanyName(seller.getCompanyName());
        response.setImage(seller.getImageData().getPath());
        return response;
    }

    @Override
    public List<SellerResponse> toResponseList(List<Seller> sellers) {
        List<SellerResponse> responses = new ArrayList<>();
        for (Seller seller : sellers) {
            responses.add(toResponse(seller));
        }
        return responses;
    }

    @Override
    public Seller toSeller(Seller seller, SellerRequest request) {
        seller.setName(request.getName());
        seller.setEmail(request.getEmail());
        seller.setPhoneNumber(request.getPhoneNumber());
        seller.setCompanyName(request.getCompanyName());
        return seller;
    }
}
