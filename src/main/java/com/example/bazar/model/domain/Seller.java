package com.example.bazar.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "sellers")
public class Seller extends User{
    private String address;
    private String phoneNumber;
    private String companyName;

    @OneToOne
    @JoinColumn(name = "image_name", referencedColumnName = "name")
    private ImageData imageData;
    @OneToMany(mappedBy = "seller")
    private List<Product> products;
    @OneToOne
    @JoinColumn(name = "system_id", referencedColumnName = "id")
    private User user;
}
