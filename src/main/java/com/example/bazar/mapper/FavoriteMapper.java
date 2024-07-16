package com.example.bazar.mapper;

import com.example.bazar.model.domain.Favorite;
import com.example.bazar.model.domain.Like;
import com.example.bazar.model.domain.Product;
import com.example.bazar.model.domain.User;

public interface FavoriteMapper {
    Favorite toDto(Product product , User user);
}
