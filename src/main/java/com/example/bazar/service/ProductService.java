package com.example.bazar.service;

import com.example.bazar.model.dto.product.CommentResponse;
import com.example.bazar.model.dto.product.ProductDetailResponse;
import com.example.bazar.model.dto.product.ProductRequest;
import com.example.bazar.model.dto.product.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    boolean likeProduct(String token, UUID productId);
    boolean addFavorite(String token, UUID productId);
    void addComment(String token, UUID productId, String content);
    ProductDetailResponse create(ProductRequest request, List<MultipartFile> files, String token);
    ProductDetailResponse getDetail(UUID id);
    List<ProductResponse> getAll(int offset, int pageSize);
    List<CommentResponse> getComments(UUID productId, int offset, int pageSize);
}
