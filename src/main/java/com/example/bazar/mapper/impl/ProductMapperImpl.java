package com.example.bazar.mapper.impl;

import com.example.bazar.mapper.ProductMapper;
import com.example.bazar.model.domain.ImageData;
import com.example.bazar.model.domain.Product;
import com.example.bazar.model.dto.product.ProductDetailResponse;
import com.example.bazar.model.dto.product.ProductRequest;
import com.example.bazar.model.dto.product.ProductResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ProductMapperImpl implements ProductMapper {
    @Override
    public ProductDetailResponse toDetailResponse(Product product) {
        ProductDetailResponse response = new ProductDetailResponse();
        List<String> imagePaths = new ArrayList<>();
        for (ImageData imageData : product.getImageData()) {
            imagePaths.add(imageData.getPath());
        }
        response.setImagePaths(imagePaths);
        response.setDescription(product.getDescription());
        response.setSellerName(product.getSeller().getName());
        response.setSellerImagePath(product.getSeller().getImageData().getPath());
        response.setLikes(product.getLikes().size());
        // response.setComments(); // todo: here should be the pagination
        response.setFavorites(product.getFavorites().size());
        return response;
    }

    @Override
    public ProductResponse toResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setImagePath(product.getImageData().get(0).getPath());
        return response;
    }

    @Override
    public List<ProductResponse> toResponseList(List<Product> products) {

        return null;
    }

    @Override
    public Product toProduct(Product product, ProductRequest request) {
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        System.out.println("It's the desc of the product: " + product.getDescription());
        return product;
    }
}
