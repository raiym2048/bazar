package com.example.bazar.mapper;

import com.example.bazar.model.domain.Comment;
import com.example.bazar.model.dto.product.CommentResponse;

import java.util.List;

public interface CommentMapper {

    List<CommentResponse> toDtoS(List<Comment> comments);
}
