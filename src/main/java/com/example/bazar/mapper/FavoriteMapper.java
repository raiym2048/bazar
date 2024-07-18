package com.example.bazar.mapper;

import com.example.bazar.model.domain.*;

public interface FavoriteMapper {
    Favorite toDto(Product product , Customer user);
}
