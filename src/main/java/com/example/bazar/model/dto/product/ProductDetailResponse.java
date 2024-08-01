package com.example.bazar.model.dto.product;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ProductDetailResponse {
    private UUID id;
    private String name;
    private double price;
    private List<String> imagePaths;
    private String description;
    private String sellerName;
    private String sellerImagePath;
    private int likes;
    private int favorites;
    private String status;
    private boolean liked;
    private boolean addedToFavorites;
}
