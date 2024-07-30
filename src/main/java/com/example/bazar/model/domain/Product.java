package com.example.bazar.model.domain;

import com.example.bazar.model.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private double price;
    private String description;

    @ElementCollection
    private List<String> images = new ArrayList<>();
    @ManyToOne
    private Seller seller;
    @OneToMany()
    private List<User> likes;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany()
    private List<User> favorites;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @ManyToOne
    @JoinColumn
    private Type type;
}
