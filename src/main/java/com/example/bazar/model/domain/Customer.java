package com.example.bazar.model.domain;

import com.example.bazar.model.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "id")
@NoArgsConstructor
public class Customer extends User{
    @OneToMany
    private List<Like> likes;
    @OneToMany
    private List<Favorite> favorites;
    @OneToMany
    private List<Comment> comments;

    public Customer(String name, String email, String password) {
        super(name, email, password, Role.CUSTOMER);
    }
}
