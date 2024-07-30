package com.example.bazar.repository.seller;

import com.example.bazar.model.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {

    List<Address> findAllByValueAndType_Value(String value, String type);
}
