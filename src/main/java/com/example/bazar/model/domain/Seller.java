package com.example.bazar.model.domain;

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
    private String name;


    @Column(nullable = false)
    @OneToMany(fetch = FetchType.LAZY)
    private List<Address> address;

    @OneToOne
    private Contact contact;



    private String image;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> products;


}
