package com.example.bazar.repository;

import com.example.bazar.model.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    boolean existsByProductAndCustomer(Product product, Customer user);
    Optional<Favorite> findByProductAndCustomer(Product product, Customer user);
}
