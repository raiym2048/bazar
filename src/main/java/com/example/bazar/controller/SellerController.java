package com.example.bazar.controller;

import com.example.bazar.model.dto.product.ProductRequest;
import com.example.bazar.model.dto.seller.SellerRequest;
import com.example.bazar.model.dto.seller.SellerResponse;
import com.example.bazar.service.ProductService;
import com.example.bazar.service.SellerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seller")
public class SellerController {
    private final SellerService sellerService;
    private final ProductService productService;


    @GetMapping("/all")
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
    public void create(@RequestPart(value = "request") String request,
                       @RequestPart(value = "files", required = false) List<MultipartFile> files,
                       @RequestHeader("Authorization") String token) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ProductRequest productRequest = mapper.readValue(request, ProductRequest.class);

        System.out.println("files: " + files.size());
        productService.create(productRequest, files, token);
    }
}
