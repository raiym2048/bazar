package com.example.bazar.service.impl;

import com.example.bazar.exception.CustomException;
import com.example.bazar.mapper.SellerMapper;
import com.example.bazar.model.domain.Product;
import com.example.bazar.model.domain.Seller;
import com.example.bazar.model.domain.User;
import com.example.bazar.model.dto.seller.SellerRequest;
import com.example.bazar.model.dto.seller.SellerResponse;
import com.example.bazar.repository.ProductRepository;
import com.example.bazar.repository.SellerRepository;
import com.example.bazar.repository.UserRepository;
import com.example.bazar.service.AuthService;
import com.example.bazar.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;
    private final SellerMapper sellerMapper;
    private final AuthService authService;
    private final MailSender mailSender;

    @Override
    public List<SellerResponse> all(int offset, int pageSize) {
        return sellerMapper.toResponseList(sellerRepository.findAll(PageRequest.of(offset, pageSize)).stream().toList());
    }

    @Override
    public SellerResponse getById(UUID id) {
        return sellerMapper.toResponse(sellerRepository.findById(id).orElseThrow(() -> new CustomException("Seller not found", HttpStatus.NOT_FOUND)));
    }

    @Override
    public SellerResponse getProfile(String token) {
        User user = authService.getUserFromToken(token);
        return sellerMapper.toResponse(sellerRepository.findById(user.getId()).orElseThrow(() -> new CustomException("Seller not found", HttpStatus.NOT_FOUND)));
    }

    @Override
    public SellerResponse update(String token, SellerRequest request) {
        User user = authService.getUserFromToken(token);
        Seller seller = sellerRepository.findById(user.getId()).orElseThrow(() -> new CustomException("Seller not found", HttpStatus.NOT_FOUND));
        /*if (!seller.getEmail().equals(request.getEmail())) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("bazar@gmail.com");
            message.setSubject("Verify the email");
            message.setText(authService.generateRandomPassword());
            message.setTo(request.getEmail());
            mailSender.send(message);
            // TODO: Later fix this code
        }*/
        Seller updatedSeller = sellerMapper.toSeller(seller, request);
        sellerRepository.save(updatedSeller);
        return sellerMapper.toResponse(updatedSeller);
    }

    @Override
    public void delete(String token) {
        User user = authService.getUserFromToken(token);
        Seller seller = sellerRepository.findById(user.getId()).orElseThrow(() -> new CustomException("Seller not found", HttpStatus.NOT_FOUND));
        sellerRepository.delete(seller);
    }
}
