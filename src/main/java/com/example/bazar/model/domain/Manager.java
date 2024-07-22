package com.example.bazar.model.domain;

import com.example.bazar.model.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "managers")
@NoArgsConstructor // todo: Search the info about this annotation
public class Manager extends User {
    private String surname;
    private String address;
    private LocalDate birthday;

    public Manager(String name, String email, String password) {
        super(name, email, password, Role.MANAGER);
    }
}
