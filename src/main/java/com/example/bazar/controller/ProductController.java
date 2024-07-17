package com.example.bazar.controller;

import com.example.bazar.model.dto.product.ProductRequest;
import com.example.bazar.model.dto.product.ProductResponse;
import com.example.bazar.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public List<ProductResponse> all() {
        return productService.all();
    }

    @PostMapping("/like/{productId}")
    public ResponseEntity<?> likeProduct(@PathVariable Long productId, @RequestHeader("Authorization") String token) {
        productService.addLike(productId, token);
        return ResponseEntity.ok("Product liked");
    }

    @DeleteMapping("/unlike/{productId}")
    public ResponseEntity<?> dislikeProduct(@PathVariable Long productId, @RequestHeader("Authorization") String token) {
        productService.removeLike(productId, token);
        return ResponseEntity.ok("Product unlike");
    }

    @PostMapping("/favorite/{productId}")
    public ResponseEntity<?> addFavorite(@PathVariable Long productId, @RequestHeader("Authorization") String token) {
        productService.addFavorite(productId, token);
        return ResponseEntity.ok("Post added to favorites successfully");
    }

    @DeleteMapping("/unfavorite/{productId}")
    public ResponseEntity<?> removeFavorite(@PathVariable Long productId, @RequestHeader("Authorization") String token) {
        productService.removeFavorite(productId, token);
        return ResponseEntity.ok("Post removed from favorites successfully");
    }

    @PostMapping("/comment/{productId}")
    public ResponseEntity<?> addComment(@PathVariable Long productId, @RequestHeader("Authorization") String token, @RequestBody String text) {
        productService.addComment(productId, token, text);
        return ResponseEntity.ok("Comment added successfully");
    }

    @DeleteMapping("/uncomment/{commentId}")
    public ResponseEntity<?> removeComment(@PathVariable Long commentId, @RequestHeader("Authorization") String token) {
        productService.removeComment(commentId, token);
        return ResponseEntity.ok("Comment removed successfully");
    }

    @PostMapping("/create")
    public void create(@RequestBody ProductRequest request, @RequestParam List<MultipartFile> files) {
        productService.create(request, files);
    }
}
