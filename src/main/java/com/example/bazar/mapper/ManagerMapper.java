package com.example.bazar.mapper;

import com.example.bazar.model.domain.Manager;
import com.example.bazar.model.dto.manager.ManagerResponse;

import java.util.List;

public interface ManagerMapper {
    ManagerResponse toResponse(Manager manager);
    List<ManagerResponse>  toResponseList(List<Manager> managers);
}
