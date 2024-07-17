package com.example.bazar.model.domain;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "images")
@Data
public class ImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private String type;
    private String path;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @OneToOne(mappedBy = "imageData")
    private Seller seller;
}
