package com.example.bazar.controller;

import com.example.bazar.model.dto.product.CommentResponse;
import com.example.bazar.model.dto.product.ProductDetailResponse;
import com.example.bazar.model.dto.product.ProductRequest;
import com.example.bazar.model.dto.product.ProductResponse;
import com.example.bazar.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/like/{productId}")
    public void likeProduct(@RequestHeader("Authorization") String token, @PathVariable Long productId) {
        productService.likeProduct(token, productId);
    }
    @PostMapping("/favorite/{productId}")
    public void addFavorite(@RequestHeader("Authorization") String token, @PathVariable Long productId) {
        productService.addFavorite(token, productId);
    }

    @PostMapping("/comment/{productId}")
    public void addComment(@RequestHeader("Authorization") String token, @PathVariable Long productId, @RequestParam String content) {
        productService.addComment(token, productId, content);
    }

    @PostMapping("/create")
    public void create(@RequestPart(value = "request") ProductRequest request,
                       @RequestPart(value = "files", required = false) List<MultipartFile> files,
                       @RequestHeader("Authorization") String token) {
        productService.create(request, files, token);
    }

    @GetMapping("/detail/{id}")
    public ProductDetailResponse getDetail(@PathVariable Long id) {
        return productService.getDetail(id);
    }

    @GetMapping("/all")
    public List<ProductResponse> getAll(@RequestParam(defaultValue = "0") int offset,
                                        @RequestParam(defaultValue = "10") int pageSize) {
        return productService.getAll(offset, pageSize);
    }

    @GetMapping("/comments/{productId}")
    public List<CommentResponse> getComments(@PathVariable Long productId,
                                             @RequestParam(defaultValue = "0") int offset,
                                             @RequestParam(defaultValue = "10") int pageSize) {
        return productService.getComments(productId, offset, pageSize);
    }
}
