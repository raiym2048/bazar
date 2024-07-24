package com.example.bazar.service.impl;

import com.example.bazar.exception.CustomException;
import com.example.bazar.service.ImageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {
    @Value("${file.system.path}")
    private String filePath;

    @Override
    public String uploadImage(MultipartFile file) {
        String imageName = System.currentTimeMillis() + "_" + file.getOriginalFilename().replaceAll("\\s+", "_");
        String fullPath = filePath + imageName;
        try {
            file.transferTo(new File(fullPath));
        } catch (IOException e) {
            log.debug("File transfer failed: {}", e.getMessage());
            throw new CustomException("File transfer failed: " + e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }

        return imageName;
    }


    @Override
    @Transactional
    public byte[] downloadImage(String name) {
        String file = filePath + name;
        byte[] image;
        try {
            image = Files.readAllBytes(new File(file).toPath());
        } catch (IOException e) {
            log.debug("Reading the file is failed: {}", e.getMessage());
            throw new CustomException(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return image;
    }

    @Override
    @Transactional
    public void delete(String name) {
       //todo delete from package
    }
}
