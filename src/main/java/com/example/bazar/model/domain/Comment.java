package com.example.bazar.model.domain;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String text;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Customer customer;
}
