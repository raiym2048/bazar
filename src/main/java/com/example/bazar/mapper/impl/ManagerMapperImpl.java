package com.example.bazar.mapper.impl;

import com.example.bazar.mapper.ManagerMapper;
import com.example.bazar.model.domain.Manager;
import com.example.bazar.model.dto.manager.ManagerResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ManagerMapperImpl implements ManagerMapper {
    @Override
    public ManagerResponse toResponse(Manager manager) {
        ManagerResponse response = new ManagerResponse();
        return response;
    }

    @Override
    public List<ManagerResponse> toResponseList(List<Manager> managers) {
        List<ManagerResponse> responses = new ArrayList<>();
        for (Manager manager : managers) {
            responses.add(toResponse(manager));
        }
        return responses;
    }
}
