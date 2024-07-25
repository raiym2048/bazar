package com.example.bazar.mapper.impl;

import com.example.bazar.mapper.CommentMapper;
import com.example.bazar.model.domain.Comment;
import com.example.bazar.model.dto.product.comment.CommentResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentMapperImpl implements CommentMapper {
    @Override
    public List<CommentResponse> toDtoS(List<Comment> comments) {
        List<CommentResponse> commentResponses = new ArrayList<>();
        for (Comment comment : comments) {
            commentResponses.add(toDto(comment));
        }
        return commentResponses;
    }

    private CommentResponse toDto(Comment comment) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setId(comment.getId());
        commentResponse.setUser(comment.getUser().getUsername());
        commentResponse.setContent(comment.getContent());
        commentResponse.setCreatedAt(comment.getCreatedAt());
        return commentResponse;
    }
}
