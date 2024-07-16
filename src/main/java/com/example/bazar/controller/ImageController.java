package com.example.bazar.controller;

import com.example.bazar.service.impl.ImageServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/image")
public class ImageController {
    private final ImageServiceImpl imageService;

    @GetMapping("/{name}")
    public ResponseEntity<?> downloadImage(@PathVariable String name) {
        byte[] imageData = imageService.downloadImage(name);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
