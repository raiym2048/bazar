package com.example.bazar.repository.seller;

import com.example.bazar.model.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Contact, UUID> {
    boolean existsByPhoneNumber(String phoneNumber);
}
