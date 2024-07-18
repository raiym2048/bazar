package com.example.bazar.repository;

import com.example.bazar.model.domain.Like;
import com.example.bazar.model.domain.Product;
import com.example.bazar.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByProduct(Product product);
    Optional<Like> findByProduct(Product product);
}
