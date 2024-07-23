package com.example.bazar.service.impl;

import com.example.bazar.exception.CustomException;
import com.example.bazar.mapper.CommentMapper;
import com.example.bazar.mapper.FavoriteMapper;
import com.example.bazar.mapper.LikeMapper;
import com.example.bazar.mapper.ProductMapper;
import com.example.bazar.model.domain.*;
import com.example.bazar.model.dto.product.CommentResponse;
import com.example.bazar.model.dto.product.ProductDetailResponse;
import com.example.bazar.model.dto.product.ProductRequest;
import com.example.bazar.model.dto.product.ProductResponse;
import com.example.bazar.repository.*;
import com.example.bazar.service.AuthService;
import com.example.bazar.service.ImageService;
import com.example.bazar.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final SellerRepository sellerRepository;
    private final AuthService authService;
    private final LikeRepository likeRepository;
    private final FavoriteRepository favoriteRepository;
    private final CommentRepository commentRepository;
    private final LikeMapper likeMapper;
    private final FavoriteMapper favoriteMapper;
    private final CommentMapper commentMapper;
    private final CustomerRepository customerRepository;
    private final ImageService imageService;
    private final ImageDataRepository imageDataRepository;

    @Override
    public boolean likeProduct(String token, UUID productId) {
        User user = authService.getUserFromToken(token);
        Product product = productRepository.findById(productId).orElseThrow(() -> new CustomException("Product not found", HttpStatus.NOT_FOUND));

        Optional<Like> existingLike = likeRepository.findByUserAndProduct(user, product);
        if (existingLike.isPresent()) {
            likeRepository.delete(existingLike.get());
            return false;
        } else {
            Like like = new Like();
            like.setUser(user);
            like.setProduct(product);
            likeRepository.save(like);
            return true;
        }
    }

    @Override
    public boolean addFavorite(String token, UUID productId) {
        User user = authService.getUserFromToken(token);
        Product product = productRepository.findById(productId).orElseThrow(() -> new CustomException("Product not found", HttpStatus.NOT_FOUND));

        Optional<Favorite> existingFavorite = favoriteRepository.findByUserAndProduct(user, product);
        if (existingFavorite.isPresent()) {
            favoriteRepository.delete(existingFavorite.get());
            return false;
        } else {
            Favorite favorite = new Favorite();
            favorite.setUser(user);
            favorite.setProduct(product);
            favoriteRepository.save(favorite);
            return true;
        }
    }

    @Override
    public void addComment(String token, UUID productId, String content) {
        User user = authService.getUserFromToken(token);
        Product product = productRepository.findById(productId).orElseThrow(() -> new CustomException("Product not found", HttpStatus.NOT_FOUND));

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUser(user);
        comment.setProduct(product);
        comment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(comment);
    }

    @Override
    public ProductDetailResponse create(ProductRequest request, List<MultipartFile> files, String token) {
        User user = authService.getUserFromToken(token);
        Seller seller = sellerRepository.findById(user.getId()).orElseThrow(() -> new CustomException("Internal server error", HttpStatus.BAD_GATEWAY));
        Product product = new Product();
        product.setSeller(seller);
        Product savedProduct = productRepository.save(product);
        List<ImageData> imageDataList = new ArrayList<>();
        for (MultipartFile file : files) {
            ImageData imageData = null;
            imageData = imageService.uploadImage(file);
            imageData.setProduct(savedProduct);
            imageDataRepository.save(imageData);
            imageDataList.add(imageData);
        }
        savedProduct.setImageData(imageDataList);
        return productMapper.toDetailResponse(productRepository.save(productMapper.toProduct(savedProduct, request)));
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
    public List<ProductResponse> getAll(int offset, int pageSize) {
        Page<Product> products = productRepository.findAll(PageRequest.of(offset, pageSize));
        return productMapper.toResponseList(products.stream().toList());
    }

    @Override
    public List<CommentResponse> getComments(UUID productId, int offset, int pageSize) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new CustomException("Product not found", HttpStatus.NOT_FOUND));
        Pageable pageable = PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));

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
