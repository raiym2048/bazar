package com.example.bazar.repository;

import com.example.bazar.model.domain.Comment;
import com.example.bazar.model.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findByProduct(Product product, Pageable pageable);
}
