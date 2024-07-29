package com.example.bazar.repository.seller;

import com.example.bazar.model.domain.AddressType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressTypeRepository extends JpaRepository<AddressType, UUID> {
    List<AddressType> findAllByValue(String type);
    AddressType findByValue(String type);
}
