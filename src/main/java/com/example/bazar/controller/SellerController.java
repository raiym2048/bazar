package com.example.bazar.controller;

import com.example.bazar.model.dto.product.ProductDetailResponse;
import com.example.bazar.model.dto.product.ProductRequest;
import com.example.bazar.model.dto.product.ProductResponse;
import com.example.bazar.model.dto.seller.SellerRequest;
import com.example.bazar.model.dto.seller.SellerResponse;
import com.example.bazar.service.ProductService;
import com.example.bazar.service.SellerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/sellers")
public class SellerController {
    private final SellerService sellerService;
    private final ProductService productService;


    @GetMapping
    public List<SellerResponse> all(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int pageSize) {
        return sellerService.all(offset, pageSize);
    }

    @GetMapping("/{id}")
    public SellerResponse getById(@PathVariable UUID id) {
        return sellerService.getById(id);
    }

    @GetMapping("/profile")
    public SellerResponse getProfile(@RequestHeader("Authorization") String token) {
        return sellerService.getProfile(token);
    }

    @PutMapping("/update")
    public SellerResponse update(@RequestHeader("Authorization") String token, @RequestBody SellerRequest request) {
        return sellerService.update(token, request);
    }


    @PostMapping("/create/product")
    public void create(@RequestPart(value = "request") ProductRequest request,
                       @RequestPart(value = "files", required = false) List<MultipartFile> files,
                       @RequestHeader("Authorization") String token) {

        System.out.println("files: " + files.size());
        productService.create(request, files, token);
    }

    @GetMapping("/product")
    public List<ProductResponse> getMyProducts(@RequestHeader(value = "Authorization") String token,
                                                    @RequestParam(defaultValue = "0") int offset,
                                                    @RequestParam(defaultValue = "10") int pageSize) {
        return productService.getMyProducts(token, offset, pageSize);
    }

//    @GetMapping("/product/{productId}")
//    public ProductDetailResponse getSellersProductDetail(@RequestHeader("Authorization") String token, @PathVariable UUID productId) {
//        return productService.getSellersProductDetail(token, productId);
//    }

    @GetMapping("/product/{sellerId}")
    public List<ProductResponse> getSellersProducts(@RequestHeader(value = "Authorization", required = false) String token,
                                                    @PathVariable UUID sellerId,
                                                    @RequestParam(defaultValue = "0") int offset,
                                                    @RequestParam(defaultValue = "10") int pageSize) {
        System.out.println("In controller");
        return productService.getSellersProduct(token, sellerId, offset, pageSize);
    }
}
