package com.example.bazar.mapper.impl;

import com.example.bazar.mapper.TypeMapper;
import com.example.bazar.model.domain.Type;
import com.example.bazar.model.dto.type.TypeRequest;
import com.example.bazar.model.dto.type.TypeResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TypeMapperImpl implements TypeMapper {
    @Override
    public TypeResponse toResponse(Type type) {
        TypeResponse response = new TypeResponse();
        response.setName(type.getName());
        response.setId(type.getId());
        return response;
    }

    @Override
    public List<TypeResponse> toResponseList(List<Type> types) {
        List<TypeResponse> responses = new ArrayList<>();
        for (Type type : types) {
            responses.add(toResponse(type));
        }
        return responses;
    }

    @Override
    public Type toType(Type type, TypeRequest request) {
        type.setName(request.getName());
        return type;
    }
}
