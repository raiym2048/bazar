package com.example.bazar.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "customers")
public class Customer extends User{
    @OneToMany
    private List<Like> likes;
    @OneToMany
    private List<Favorite> favorites;
    @OneToMany
    private List<Comment> comments;
}
