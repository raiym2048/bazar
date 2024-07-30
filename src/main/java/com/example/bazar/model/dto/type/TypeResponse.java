package com.example.bazar.model.dto.type;

import lombok.Data;

import java.util.UUID;

@Data
public class TypeResponse {
    private UUID id;
    private String name;
}
