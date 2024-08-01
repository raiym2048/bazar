package com.example.bazar.mapper;

import com.example.bazar.model.domain.Product;
import com.example.bazar.model.domain.User;
import com.example.bazar.model.dto.product.ProductDetailResponse;
import com.example.bazar.model.dto.product.ProductRequest;
import com.example.bazar.model.dto.product.ProductResponse;

import java.util.List;

public interface ProductMapper {

    ProductResponse toResponse(Product product, User user);

    List<ProductResponse> toResponseList(List<Product> products, User user);

    Product toProduct(Product product, ProductRequest request);
}
