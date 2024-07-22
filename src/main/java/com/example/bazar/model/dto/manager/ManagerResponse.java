package com.example.bazar.model.dto.manager;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ManagerResponse {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String address;
    private LocalDate birthday;
}
