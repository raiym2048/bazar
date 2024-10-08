package com.example.bazar.repository;

import com.example.bazar.model.domain.Type;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TypeRepository extends JpaRepository<Type, UUID> {
    Optional<Type> findByName(String name);
    @Transactional
    void deleteByName(String name);
}
