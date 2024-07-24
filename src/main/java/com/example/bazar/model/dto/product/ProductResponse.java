package com.example.bazar.model.dto.product;

import com.example.bazar.model.dto.product.comment.CommentResponse;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ProductResponse {

    private UUID id;

    private String name;
    private double price;
    private String description;

    private List<String> images;

    private String seller;
    private int likeCount;

    private List<CommentResponse> comments;

    private int favoriteCount;

    private String status;

    private Boolean liked;
    private Boolean favorited;
}
