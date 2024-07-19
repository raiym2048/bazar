package com.example.bazar.service.impl;

import com.example.bazar.config.JwtService;
import com.example.bazar.exception.CustomException;
import com.example.bazar.mapper.CommentMapper;
import com.example.bazar.mapper.FavoriteMapper;
import com.example.bazar.mapper.LikeMapper;
import com.example.bazar.mapper.ProductMapper;
import com.example.bazar.model.domain.*;
import com.example.bazar.model.dto.product.ProductDetailResponse;
import com.example.bazar.model.dto.product.ProductRequest;
import com.example.bazar.repository.*;
import com.example.bazar.service.AuthService;
import com.example.bazar.service.ImageService;
import com.example.bazar.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @Override
    public void addLike(Long productId, String token) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()){
            throw new CustomException("Product with id "+productId+" not found" , HttpStatus.NOT_FOUND);
        }
        User user = authService.getUserFromToken(token);
        Customer customer = customerRepository.findById(user.getId())
                .orElseThrow(() -> new CustomException("User with id " + user.getId() + " not found", HttpStatus.NOT_FOUND));
        if(likeRepository.existsByProduct(productOptional.get())){
            throw new CustomException("You already liked this product", HttpStatus.BAD_REQUEST);
        }
        Like like = likeMapper.toLikeDto(productOptional.get(), customer);
        likeRepository.save(like);
    }
    @Override
    public void removeLike(Long productId, String token) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()){
            throw new CustomException("Product with id "+productId+" not found" , HttpStatus.NOT_FOUND);
        }
        User user = authService.getUserFromToken(token);
        if(likeRepository.existsByProduct(productOptional.get())){
            throw new CustomException("You already liked this product", HttpStatus.BAD_REQUEST);
        }
        Like like = likeRepository.findByProduct(productOptional.get())
                .orElseThrow(() -> new CustomException("Like not found for product id " + productId, HttpStatus.NOT_FOUND));
        likeRepository.delete(like);
    }

    @Override
    public void addFavorite(Long productId, String token) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()){
            throw new CustomException("Product with id "+productId+" not found" , HttpStatus.NOT_FOUND);
        }
        User user = authService.getUserFromToken(token);
        Customer customer = customerRepository.findById(user.getId())
                .orElseThrow(() -> new CustomException("User with id " + user.getId() + " not found", HttpStatus.NOT_FOUND));
        if(likeRepository.existsByProduct(productOptional.get())){
            throw new CustomException("You already liked this product", HttpStatus.BAD_REQUEST);
        }
        if (favoriteRepository.existsByProductAndCustomer(productOptional.get(), customer)) {
            throw new CustomException("Product is already in user's favorites" , HttpStatus.BAD_REQUEST);
        }
        Favorite favorite = favoriteMapper.toDto(productOptional.get(), customer);
        favoriteRepository.save(favorite);
    }

    @Override
    public void removeFavorite(Long productId,String token) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()){
            throw new CustomException("Product with id "+productId+" not found" , HttpStatus.NOT_FOUND);
        }
        User user = authService.getUserFromToken(token);
        Customer customer = customerRepository.findById(user.getId())
                .orElseThrow(() -> new CustomException("User with id " + user.getId() + " not found", HttpStatus.NOT_FOUND));
        if(likeRepository.existsByProduct(productOptional.get())){
            throw new CustomException("You already liked this product", HttpStatus.BAD_REQUEST);
        }
        if (!favoriteRepository.existsByProductAndCustomer(productOptional.get(), customer)) {
            throw new CustomException("Product is not in user's favorites" , HttpStatus.BAD_REQUEST);
        }
        Favorite favorite = favoriteRepository.findByProductAndCustomer(productOptional.get(), customer)
                .orElseThrow(() -> new CustomException("Favorite not found for product id " + productId + " and user id " + user.getId(), HttpStatus.NOT_FOUND));
        favoriteRepository.delete(favorite);
    }

    @Override
    public void addComment(Long productId, String token, String text) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()){
            throw new CustomException("Product with id "+productId+" not found" , HttpStatus.NOT_FOUND);
        }
        User user = authService.getUserFromToken(token);
        Customer customer = customerRepository.findById(user.getId())
                .orElseThrow(() -> new CustomException("User with id " + user.getId() + " not found", HttpStatus.NOT_FOUND));
        if(likeRepository.existsByProduct(productOptional.get())){
            throw new CustomException("You already liked this product", HttpStatus.BAD_REQUEST);
        }
        if(text.isEmpty()){
            throw new CustomException("Comment text can't be empty", HttpStatus.BAD_REQUEST);
        }
        Comment comment = commentMapper.toDtoComment(productOptional.get(), customer, text);
        commentRepository.save(comment);
    }

    @Override
    public void removeComment(Long commentId , String token) {
        User user = authService.getUserFromToken(token);
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException("Comment with id " + commentId + " not found", HttpStatus.NOT_FOUND));
        if(comment.getCustomer().getId() != user.getId()){
            throw new CustomException("You can't delete this comment", HttpStatus.FORBIDDEN);
        }
        commentRepository.delete(comment);
    }

    @Override
    public void create(ProductRequest request, List<MultipartFile> files, String token) {

        User user = authService.getUserFromToken(token);
        Seller seller = sellerRepository.findById(user.getId()).orElseThrow(() -> new CustomException("Internal server error", HttpStatus.BAD_GATEWAY));
        Product product = new Product();
        product.setSeller(seller);
        List<ImageData> imageDataList = new ArrayList<>();
        for (MultipartFile file : files) {
            ImageData imageData = null;
            imageData = imageService.uploadImage(file);
            imageDataList.add(imageData);
        }
        product.setImageData(imageDataList);
        productRepository.save(productMapper.toProduct(product, request));
    }

    @Override
    public ProductDetailResponse getDetail(Long id) {
//        if ()
        return null;
    }
}
