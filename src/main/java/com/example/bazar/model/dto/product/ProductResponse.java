package com.example.bazar.model.dto.product;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ProductResponse {

    private UUID id;
    private String name;
    private List<String> images;
    private String seller;
    private String description;
    private double price;
    private int likeCount;

    private int commentCount;

    private int favoriteCount;
    private boolean liked;
    private boolean favorited;
    private String status;
}
