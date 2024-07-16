package com.example.bazar.mapper;

import com.example.bazar.model.domain.Comment;
import com.example.bazar.model.domain.Product;
import com.example.bazar.model.domain.User;

public interface CommentMapper {
    Comment toDtoComment(Product product, User user, String text);
}
