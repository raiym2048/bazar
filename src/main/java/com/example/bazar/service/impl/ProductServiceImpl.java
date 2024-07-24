package com.example.bazar.service.impl;

import com.example.bazar.exception.CustomException;
import com.example.bazar.mapper.CommentMapper;
import com.example.bazar.mapper.FavoriteMapper;
import com.example.bazar.mapper.ProductMapper;
import com.example.bazar.model.domain.Comment;
import com.example.bazar.model.domain.Product;
import com.example.bazar.model.domain.Seller;
import com.example.bazar.model.domain.User;
import com.example.bazar.model.dto.product.CommentResponse;
import com.example.bazar.model.dto.product.ProductDetailResponse;
import com.example.bazar.model.dto.product.ProductRequest;
import com.example.bazar.model.dto.product.ProductResponse;
import com.example.bazar.model.enums.ProductStatus;
import com.example.bazar.repository.CommentRepository;
import com.example.bazar.repository.ProductRepository;
import com.example.bazar.repository.SellerRepository;
import com.example.bazar.service.AuthService;
import com.example.bazar.service.ImageService;
import com.example.bazar.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final SellerRepository sellerRepository;
    private final AuthService authService;
    private final CommentRepository commentRepository;
    private final FavoriteMapper favoriteMapper;
    private final CommentMapper commentMapper;
    private final ImageService imageService;

    @Override
    public void likeProduct(String token, UUID productId) {
        User user = authService.getUserFromToken(token);
        Product product = productRepository.findById(productId).orElseThrow(() -> new CustomException("Product not found", HttpStatus.NOT_FOUND));


        if (product.getLikes().contains(user)) {
            product.getLikes().remove(user);
        } else {
            product.getLikes().add(user);
        }
        productRepository.save(product);
    }

    @Override
    public void addFavorite(String token, UUID productId) {
        User user = authService.getUserFromToken(token);
        Product product = productRepository.findById(productId).orElseThrow(() -> new CustomException("Product not found", HttpStatus.NOT_FOUND));

        if (product.getFavorites().contains(user)) {
            product.getFavorites().remove(user);
        } else {
            product.getFavorites().add(user);
        }
        productRepository.save(product);
    }

    @Override
    public void addComment(String token, UUID productId, String content) {
        User user = authService.getUserFromToken(token);
        Product product = productRepository.findById(productId).orElseThrow(() -> new CustomException("Product not found", HttpStatus.NOT_FOUND));

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUser(user);
        comment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(comment);
    }

    @Override
    public void create(ProductRequest request, List<MultipartFile> files, String token) {
        User user = authService.getUserFromToken(token);
        Seller seller = user.getSeller();
        if (seller == null)
            throw new CustomException("Seller not found", HttpStatus.NOT_FOUND);
        Product product = new Product();
        product.setSeller(seller);
        List<String> imageDataList = new ArrayList<>();
        for (MultipartFile file : files) {
            String image = imageService.uploadImage(file);

            imageDataList.add(image);
        }
        product.setStatus(ProductStatus.WAITING);
        product.setImages(imageDataList);
        productRepository.save(productMapper.toProduct(product, request));
    }

    @Override
    public ProductDetailResponse getDetail(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new CustomException("Product not found", HttpStatus.NOT_FOUND));
        if (product.getSeller() == null) {
            throw new CustomException("Seller not found", HttpStatus.NOT_FOUND);
        }
        return productMapper.toDetailResponse(product);
    }

    @Override
    public List<ProductResponse> getAll(int offset, int pageSize, String token) {
        Page<Product> products = productRepository.findAllByStatus(PageRequest.of(offset, pageSize), ProductStatus.ACCEPTED);
        if (token != null) {
            return productMapper.toResponseList(products, authService.getUserFromToken(token));
        }
        return productMapper.toResponseList(products, null);
    }

    @Override
    public List<CommentResponse> getComments(UUID productId, int offset, int pageSize) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new CustomException("Product not found", HttpStatus.NOT_FOUND));
        Pageable pageable = PageRequest.of(offset, pageSize);

        return commentRepository.findByProduct(product, pageable).stream()
                .map(comment -> {
                    CommentResponse response = new CommentResponse();
                    response.setUserName(comment.getUser().getName());
                    response.setContent(comment.getContent());
                    response.setCreatedAt(comment.getCreatedAt());
                    return response;
                })
                .collect(Collectors.toList());
    }
}
