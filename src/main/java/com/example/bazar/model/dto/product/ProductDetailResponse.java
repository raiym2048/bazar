package com.example.bazar.model.dto.product;

import lombok.Data;

import java.util.List;

@Data
public class ProductDetailResponse {
    private String name;
    private double price;
    private List<String> imagePaths;
    private String description;
    private String sellerName;
    private String sellerImagePath;
    private Integer likes;
    private Integer favorites;
}
