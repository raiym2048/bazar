package com.example.bazar.repository;

import com.example.bazar.model.domain.ImageData;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImageDataRepository extends JpaRepository<ImageData, UUID> {
    Optional<ImageData> findByName(String name);

    @Transactional
    void deleteByName(String name);
}
