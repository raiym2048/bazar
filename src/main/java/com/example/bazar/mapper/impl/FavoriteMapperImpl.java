package com.example.bazar.mapper.impl;

import com.example.bazar.mapper.FavoriteMapper;
import com.example.bazar.model.domain.*;
import org.springframework.stereotype.Component;

@Component
public class FavoriteMapperImpl  implements FavoriteMapper {

    @Override
    public Favorite toDto(Product product, Customer user) {
        Favorite favorite = new Favorite();
        favorite.setProduct(product);
        favorite.setCustomer(user);
        return favorite;
    }
}
