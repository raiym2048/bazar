package com.example.bazar.controller;

import com.example.bazar.model.dto.product.CommentResponse;
import com.example.bazar.model.dto.product.ProductDetailResponse;
import com.example.bazar.model.dto.product.ProductResponse;
import com.example.bazar.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/like/{productId}")
    public void likeProduct(@RequestHeader("Authorization") String token, @PathVariable UUID productId) {
        productService.likeProduct(token, productId);
    }

    @PostMapping("/favorite/{productId}")
    public void addFavorite(@RequestHeader("Authorization") String token, @PathVariable UUID productId) {
        productService.addFavorite(token, productId);
    }

    @PostMapping("/comment/{productId}")
    public void addComment(@RequestHeader("Authorization") String token, @PathVariable UUID productId, @RequestParam String content) {
        productService.addComment(token, productId, content);
    }

    @GetMapping("/detail/{id}")
    public ProductDetailResponse getDetail(@PathVariable UUID id) {
        return productService.getDetail(id);
    }

    @GetMapping("/all")
    public List<ProductResponse> getAll(@RequestParam(defaultValue = "0") int offset,
                                        @RequestParam(defaultValue = "10") int pageSize,
                                        @RequestHeader(required = false, name = "Authorization") String token) {
        return productService.getAll(offset, pageSize, token);
    }

    @GetMapping("/comments/{productId}")
    public List<CommentResponse> getComments(@PathVariable UUID productId,
                                             @RequestParam(defaultValue = "0") int offset,
                                             @RequestParam(defaultValue = "10") int pageSize) {
        return productService.getComments(productId, offset, pageSize);
    }
}
