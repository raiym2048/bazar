package com.example.bazar.model.dto.manager;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class ManagerResponse {
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private String address;
    private LocalDate birthday;
}
