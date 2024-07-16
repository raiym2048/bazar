package com.example.bazar.mapper;

import com.example.bazar.model.domain.Customer;
import com.example.bazar.model.domain.Like;
import com.example.bazar.model.domain.Product;
import com.example.bazar.model.domain.User;

public interface LikeMapper {
    Like toLikeDto(Product product , Customer user);
}
