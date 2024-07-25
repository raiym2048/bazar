package com.example.bazar.mapper.impl;

import com.example.bazar.mapper.CommentMapper;
import com.example.bazar.mapper.ProductMapper;
import com.example.bazar.model.domain.Product;
import com.example.bazar.model.domain.User;
import com.example.bazar.model.dto.product.ProductDetailResponse;
import com.example.bazar.model.dto.product.ProductRequest;
import com.example.bazar.model.dto.product.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapperImpl implements ProductMapper {
    private final CommentMapper commentMapper;

    public ProductMapperImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public ProductDetailResponse toDetailResponse(Product product) {
        ProductDetailResponse response = new ProductDetailResponse();
        List<String> imagePaths = product.getImages();

        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setImagePaths(imagePaths);
        response.setDescription(product.getDescription());
        response.setSellerName(product.getSeller().getCompanyName());
        response.setSellerImagePath(product.getSeller().getImage());

        if (product.getLikes() != null) {
            response.setLikes(product.getLikes().size());
        } else {
            response.setLikes(0);
        }
        if (product.getFavorites() != null) {
            response.setFavorites(product.getFavorites().size());
        } else {
            response.setFavorites(0);
        }
        return response;
    }

    @Override
    public ProductResponse toResponse(Product product, User user) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setImages(product.getImages());
        response.setSeller(product.getSeller().getCompanyName());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setStatus(product.getStatus().name());
        response.setFavoriteCount(product.getFavorites().size());
        response.setLikeCount(product.getLikes().size());
        response.setComments(commentMapper.toDtoS(product.getComments()));
        response.setPrice(product.getPrice());
        if (user != null) {
            response.setLiked(product.getLikes().contains(user));
            response.setFavorited(product.getFavorites().contains(user));
        }
        return response;
    }

    @Override
    public List<ProductResponse> toResponseList(Page<Product> products, User user) {
        return products.stream()
                .map(product -> toResponse(product, user))
                .collect(Collectors.toList());
    }

    @Override
    public Product toProduct(Product product, ProductRequest request) {
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        return product;
    }
}
