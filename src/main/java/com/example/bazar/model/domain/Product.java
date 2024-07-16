package com.example.bazar.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String name;
    private String description;
    private String price;
    @OneToMany
    private List<Like> likes;
    @OneToMany
    private List<Comment> comments;

}
