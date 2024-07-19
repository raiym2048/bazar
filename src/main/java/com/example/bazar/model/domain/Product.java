package com.example.bazar.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

import java.util.List;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String name;
    private double price;
    private String description;

    @OneToMany(mappedBy = "product")
    private List<ImageData> imageData;
    @ManyToOne
    private Seller seller;
    @OneToMany(mappedBy = "product")
    private List<Like> likes;
    @OneToMany(mappedBy = "product")
    private List<Comment> comments;
    @OneToMany(mappedBy = "product")
    private List<Favorite> favorites;
}
