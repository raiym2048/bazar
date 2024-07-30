package com.example.bazar.service.impl;

import com.example.bazar.exception.CustomException;
import com.example.bazar.mapper.TypeMapper;
import com.example.bazar.model.domain.Type;
import com.example.bazar.model.dto.type.TypeRequest;
import com.example.bazar.model.dto.type.TypeResponse;
import com.example.bazar.repository.TypeRepository;
import com.example.bazar.service.TypeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TypeServiceImpl implements TypeService {
    private final TypeRepository typeRepository;
    private final TypeMapper typeMapper;

    @Override
    public List<TypeResponse> all(int offset, int pageSize) {
        return typeMapper.toResponseList(typeRepository.findAll(PageRequest.of(offset, pageSize)).stream().toList());
    }

    @Override
    public TypeResponse getByName(String name) {
        return typeMapper.toResponse(typeRepository.findByName(name).orElseThrow(() -> new CustomException("Type not found", HttpStatus.NOT_FOUND)));
    }

    @Override
    public TypeResponse updateByName(String name, TypeRequest request) {
        Type type = typeRepository.findByName(name).orElseThrow(() -> new CustomException("Type not found", HttpStatus.NOT_FOUND));
        return typeMapper.toResponse(typeRepository.save(typeMapper.toType(type, request)));
    }

    @Override
    public void deleteByName(String name) {
        typeRepository.deleteByName(name);
    }

    @Override
    public TypeResponse create(TypeRequest request) {
        return typeMapper.toResponse(typeRepository.save(typeMapper.toType(new Type(), request)));
    }
}
