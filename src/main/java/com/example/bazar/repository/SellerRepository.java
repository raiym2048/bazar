package com.example.bazar.repository;

import com.example.bazar.model.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {

}
