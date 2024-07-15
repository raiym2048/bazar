package com.example.bazar.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    private List<Product> products;
}
