package com.example.bazar.model.dto.product;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CommentResponse {
    private UUID id;
    private String content;
    private LocalDateTime createdAt;
    private String user;
}
