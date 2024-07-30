package com.example.bazar.mapper;

import com.example.bazar.model.domain.Type;
import com.example.bazar.model.dto.type.TypeRequest;
import com.example.bazar.model.dto.type.TypeResponse;

import java.util.List;

public interface TypeMapper {
    TypeResponse toResponse(Type type);
    List<TypeResponse> toResponseList(List<Type> types);
    Type toType(Type type, TypeRequest request);
}
