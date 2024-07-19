package com.example.bazar.service;

import com.example.bazar.model.dto.product.CommentResponse;
import com.example.bazar.model.dto.product.ProductDetailResponse;
import com.example.bazar.model.dto.product.ProductRequest;
import com.example.bazar.model.dto.product.ProductResponse;
import org.hibernate.query.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ProductService {
    void likeProduct(String token, Long productId);
    void addFavorite(String token, Long productId);
    void addComment(String token, Long productId, String content);
    void create(ProductRequest request, List<MultipartFile> files, String token);
    ProductDetailResponse getDetail(Long id);
    List<ProductResponse> getAll(int offset, int pageSize);
    List<CommentResponse> getComments(Long productId, int offset, int pageSize);
}
