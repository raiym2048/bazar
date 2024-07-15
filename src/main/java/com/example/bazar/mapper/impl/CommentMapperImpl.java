package com.example.bazar.mapper.impl;

import com.example.bazar.mapper.CommentMapper;
import com.example.bazar.model.domain.Comment;
import com.example.bazar.model.domain.Product;
import com.example.bazar.model.domain.User;
import org.springframework.stereotype.Component;

@Component
public class CommentMapperImpl implements CommentMapper {
    @Override
    public Comment toDtoComment(Product product, User user, String text) {
        Comment comment = new Comment();
        comment.setProduct(product);
        comment.setUser(user);
        comment.setText(text);
        return comment;
    }
}
