package com.example.bazar.service;

import com.example.bazar.model.dto.product.ProductRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    void addLike(Long productId, String token);
    void removeLike(Long productId, String token);
    void addFavorite(Long productId, String token);
    void removeFavorite(Long productId,String token);
    void addComment(Long productId, String token, String text);
    void removeComment(Long commentId , String token);

    void create(ProductRequest request, List<MultipartFile> files);
}
