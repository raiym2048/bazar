package com.example.bazar.mapper;

import com.example.bazar.model.domain.Product;
import com.example.bazar.model.dto.product.ProductDetailResponse;
import com.example.bazar.model.dto.product.ProductRequest;
import com.example.bazar.model.dto.product.ProductResponse;

import java.util.List;
import java.util.Map;

public interface ProductMapper {
    ProductDetailResponse toDetailResponse(Product product);
    ProductResponse toResponse(Product product);
    List<ProductResponse> toResponseList(List<Product> products);
    Product toProduct(Product product, ProductRequest request);
}
