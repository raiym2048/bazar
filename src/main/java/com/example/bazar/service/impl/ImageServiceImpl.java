package com.example.bazar.service.impl;

import com.example.bazar.exception.CustomException;
import com.example.bazar.model.domain.ImageData;
import com.example.bazar.repository.ImageDataRepository;
import com.example.bazar.service.ImageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
@AllArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {
    private final ImageDataRepository imageDataRepository;

    @Override
    public ImageData uploadImage(MultipartFile file) {
        ImageData imageData = new ImageData();
        String imageName = System.currentTimeMillis() + file.getOriginalFilename();
        imageData.setName(imageName);
        imageData.setPath("localhost:2020/image/" + imageName);
        try {
            imageData.setImageData(compressImage(file.getBytes()));
        } catch (IOException e) {
            log.info("Error during compressing the image: {}", e.getMessage());
        }
        imageDataRepository.save(imageData);
        return imageData;
    }

    @Override
    public byte[] downloadImage(String name) {
        ImageData imageData = imageDataRepository.findByName(name).orElseThrow(() -> new CustomException("Image not found", HttpStatus.NOT_FOUND));
        return decompressImage(imageData.getImageData());
    }

    public static byte[] compressImage(byte[] image) {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(image.length);
        byte[] tmp = new byte[4*1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }

        try {
            outputStream.close();
        } catch (Exception e) {
            log.info("Error writing the image: {}", e.getMessage());
        }
        return outputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] image) {
        Inflater inflater = new Inflater();
        inflater.setInput(image);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(image.length);
        byte[] tmp = new byte[4*1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception e) {
            log.info("Error writing the image: {}", e.getMessage());
        }
        return outputStream.toByteArray();
    }
}
