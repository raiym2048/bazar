package com.example.bazar.repository;

import com.example.bazar.model.domain.Like;
import com.example.bazar.model.domain.Product;
import com.example.bazar.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByProductAndUser(Product product, User user);
    Optional<Like> findByProductAndUser(Product product, User user);
}
