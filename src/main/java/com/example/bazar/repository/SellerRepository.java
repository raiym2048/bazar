package com.example.bazar.repository;

import com.example.bazar.model.domain.Seller;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface SellerRepository extends JpaRepository<Seller, UUID> {
}
