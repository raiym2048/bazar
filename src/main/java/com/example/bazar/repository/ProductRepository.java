package com.example.bazar.repository;

import com.example.bazar.model.domain.Product;
import com.example.bazar.model.domain.Seller;
import com.example.bazar.model.enums.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByStatus(PageRequest of, ProductStatus status);
    List<Product> findAllBySeller(Seller seller, PageRequest pageRequest);
}
