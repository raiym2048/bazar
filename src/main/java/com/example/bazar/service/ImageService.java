package com.example.bazar.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String uploadImage(MultipartFile file);

    byte[] downloadImage(String name);

    void delete(String name);
}
