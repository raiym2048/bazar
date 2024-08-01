package com.example.bazar.controller;

import com.example.bazar.service.impl.ImageServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/image")
public class ImageController {
    private final ImageServiceImpl imageService;

    @PostMapping("/upload")
    public String uploadImage(MultipartFile file) {
        return imageService.uploadImage(file);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> downloadImage(@PathVariable String name) {
        byte[] imageData = imageService.downloadImage(name);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

}
