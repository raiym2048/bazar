package com.example.bazar.mapper.impl;

import com.example.bazar.mapper.LikeMapper;
import com.example.bazar.model.domain.Like;
import com.example.bazar.model.domain.Product;
import com.example.bazar.model.domain.User;

import org.springframework.stereotype.Component;

@Component

public class LikeMapperImpl implements LikeMapper {

    @Override
    public Like toLikeDto(Product product, User user) {
        Like like = new Like();
        like.setProduct(product);
        like.setUser(user);
        return like;
    }
}
