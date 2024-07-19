package com.example.bazar.model.dto.product;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponse {
    private String userName;
    private String content;
    private LocalDateTime createdAt;
}
