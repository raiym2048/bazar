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

@Setter
@Getter
@Entity
@Table(name = "sellers")
@NoArgsConstructor
public class Seller extends User {
    private String address;
    private String phoneNumber;
    private String companyName;
  
    @OneToOne
    @JoinColumn(name = "image_name", referencedColumnName = "name")
    private ImageData imageData;
    @OneToMany(mappedBy = "seller")
    private List<Product> products;

    public Seller(String name, String email, String password) {
        super(name, email, password, Role.SELLER);
    }
}
