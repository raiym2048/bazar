package com.example.bazar.model.dto.product;

import lombok.Data;

import java.util.List;

@Data
public class ProductDetailResponse {
    private List<String> imagePaths;
    private String description;
    private String sellerName;
    private String sellerImagePath;
    private int likes;
    private List<String> comments;
    private int favorites;
}
