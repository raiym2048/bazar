package com.example.bazar.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Data
@Table(name = "likes")
public class Like implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private boolean liked;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Product product;
}
