package com.example.bazar.service;

import com.example.bazar.model.domain.ImageData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    ImageData uploadImage(MultipartFile file);
    byte[] downloadImage(String name);
    void delete(String name);
}
