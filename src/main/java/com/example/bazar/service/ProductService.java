package com.example.bazar.service;

public interface ProductService {
    void addLike(Long productId, String token);
    void removeLike(Long productId, String token);
    void addFavorite(Long productId, String token);
    void removeFavorite(Long productId,String token);
    void addComment(Long productId, String token, String text);
    void removeComment(Long commentId , String token);

}
