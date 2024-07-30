package com.example.bazar.controller;

import com.example.bazar.model.dto.type.TypeRequest;
import com.example.bazar.model.dto.type.TypeResponse;
import com.example.bazar.service.TypeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/types")
public class TypeController {
    private final TypeService typeService;

    @GetMapping
    public List<TypeResponse> all(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int pageSize) {
        return typeService.all(offset, pageSize);
    }

    @GetMapping("/{name}")
    public TypeResponse getByName(@PathVariable String name) {
        return typeService.getByName(name);
    }

    @PutMapping("/update/{name}")
    public TypeResponse updateByName(@PathVariable String name, @RequestBody TypeRequest request) {
        return typeService.updateByName(name, request);
    }

    @DeleteMapping("/delete/{name}")
    public void deleteByName(@PathVariable String name) {
        typeService.deleteByName(name);
    }

    @PostMapping("/create")
    public TypeResponse create(@RequestBody TypeRequest request) {
        return typeService.create(request);
    }
}
