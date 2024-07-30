package com.example.bazar.service;

import com.example.bazar.model.dto.type.TypeRequest;
import com.example.bazar.model.dto.type.TypeResponse;

import java.util.List;

public interface TypeService {
    List<TypeResponse> all(int offset, int pageSize);
    TypeResponse getByName(String name);
    TypeResponse updateByName(String name, TypeRequest request);
    void deleteByName(String name);
    TypeResponse create(TypeRequest request);
}
