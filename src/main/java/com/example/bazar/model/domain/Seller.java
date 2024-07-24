package com.example.bazar.model.domain;

import com.example.bazar.model.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "sellers")
@NoArgsConstructor
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String address;
    private String phoneNumber;
    private String companyName;

    private String image;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> products;


}
