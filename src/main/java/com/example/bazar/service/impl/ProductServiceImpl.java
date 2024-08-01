package com.example.bazar.service.impl;

import com.example.bazar.exception.CustomException;
import com.example.bazar.mapper.ProductMapper;
import com.example.bazar.model.domain.*;
import com.example.bazar.model.dto.product.CommentResponse;
import com.example.bazar.model.dto.product.ProductDetailResponse;
import com.example.bazar.model.dto.product.ProductRequest;
import com.example.bazar.model.dto.product.ProductResponse;
import com.example.bazar.model.enums.ProductStatus;
import com.example.bazar.repository.CommentRepository;
import com.example.bazar.repository.ProductRepository;
import com.example.bazar.repository.SellerRepository;
import com.example.bazar.repository.TypeRepository;
import com.example.bazar.service.AuthService;
import com.example.bazar.service.ImageService;
import com.example.bazar.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
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
    private final ImageService imageService;
    private final TypeRepository typeRepository;

    @Override
    public boolean likeProduct(String token, UUID productId) {
        User user = authService.getUserFromToken(token);
        Product product = productRepository.findById(productId).orElseThrow(() -> new CustomException("Product not found", HttpStatus.NOT_FOUND));

        if (product.getLikes().contains(user)) {
            product.getLikes().remove(user);
            productRepository.save(product);
            return false;
        } else {
            product.getLikes().add(user);
            productRepository.save(product);
            return true;
        }
    }

    @Override
    public boolean addFavorite(String token, UUID productId) {
        User user = authService.getUserFromToken(token);
        Product product = productRepository.findById(productId).orElseThrow(() -> new CustomException("Product not found", HttpStatus.NOT_FOUND));

        if (product.getFavorites().contains(user)) {
            product.getFavorites().remove(user);
            productRepository.save(product);
            return false;
        } else {
            product.getFavorites().add(user);
            productRepository.save(product);
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
        Type type = typeRepository.findByName(request.getType()).orElseThrow(() -> new CustomException("Type not found", HttpStatus.NOT_FOUND));
        User user = authService.getUserFromToken(token);
        Seller seller = user.getSeller();
        if (seller == null)
            throw new CustomException("Seller not found", HttpStatus.NOT_FOUND);
        Product product = new Product();
        product.setType(type);
        product.setSeller(seller);
        List<String> imageDataList = new ArrayList<>();
        for (MultipartFile file : files) {
            String image = imageService.uploadImage(file);
            imageDataList.add(image);
        }
        product.setStatus(ProductStatus.WAITING);
        product.setImages(imageDataList);
        return productMapper.toDetailResponse(productRepository.save(productMapper.toProduct(product, request)), user);
    }

    @Override
    public ProductDetailResponse getDetail(String token, UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new CustomException("Product not found", HttpStatus.NOT_FOUND));
        if (product.getSeller() == null) {
            throw new CustomException("Seller not found", HttpStatus.NOT_FOUND);
        }
        User user = null;
        if (token != null) {
            user = authService.getUserFromToken(token);
        }
        return productMapper.toDetailResponse(product, user);
    }

    @Override
    public List<ProductResponse> getAll(int offset, int pageSize, String token) {
        List<Product> products = productRepository.findAllByStatus(PageRequest.of(offset, pageSize), ProductStatus.ACCEPTED);
        System.out.println("the size:"+products.size());
        if (token != null) {
            return productMapper.toResponseList(products, authService.getUserFromToken(token));
        }
        return productMapper.toResponseList(products, null);
    }

    @Override
    public List<CommentResponse> getComments(UUID productId, int offset, int pageSize) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new CustomException("Product not found", HttpStatus.NOT_FOUND));
        Pageable pageable = PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));

        return commentRepository.findByProduct(product, pageable).stream()
                .map(comment -> {
                    CommentResponse response = new CommentResponse();
                    response.setId(comment.getId());
                    response.setUser(comment.getUser().getName());
                    response.setContent(comment.getContent());
                    response.setCreatedAt(comment.getCreatedAt());
                    return response;
                })
                .collect(Collectors.toList());
    }

    public List<ProductResponse> getMyProducts(String token, int offset, int pageSize) {
        User user = authService.getUserFromToken(token);
        List<Product> products = productRepository.findAllBySeller(user.getSeller(), PageRequest.of(offset, pageSize));
        return productMapper.toResponseList(products, user);
    }

    @Override
    public ProductDetailResponse getSellersProductDetail(String token, UUID productId) {
        User user = authService.getUserFromToken(token);
        Product product = productRepository.findById(productId).orElseThrow(() -> new CustomException("Product not found", HttpStatus.NOT_FOUND));
        if (user.getSeller().getProducts().contains(product)) {
            return productMapper.toDetailResponse(product, user);
        }
        throw new CustomException("Product doesn't belong to seller", HttpStatus.BAD_REQUEST);
    }

    @Override
    public List<ProductResponse> getSellersProduct(String token, UUID sellerId, int offset, int pageSize) {
        System.out.println("In service");
        User user = token!=null? authService.getUserFromToken(token): null;
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(() -> new CustomException("Seller not found", HttpStatus.NOT_FOUND));
        System.out.println("Seller id: " + seller.getId());
        return productMapper.toResponseList(productRepository.findAllBySeller(seller, PageRequest.of(offset, pageSize)), user);
    }
}
