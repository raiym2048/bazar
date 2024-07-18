package com.example.bazar.mapper.impl;

import com.example.bazar.mapper.ProductMapper;
import com.example.bazar.model.domain.ImageData;
import com.example.bazar.model.domain.Product;
import com.example.bazar.model.dto.product.ProductResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapperImpl implements ProductMapper {
    @Override
    public ProductResponse toResponse(Product product) {
        ProductResponse response = new ProductResponse();
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
    public List<ProductResponse> toResponseList(List<Product> products) {
        List<ProductResponse> responses = new ArrayList<>();
        for(Product product : products) {
            responses.add(toResponse(product));
        }
        return responses; // todo: Here the response's size should be less then its
    }
}
