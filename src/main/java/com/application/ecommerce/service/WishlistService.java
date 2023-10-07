package com.application.ecommerce.service;

import com.application.ecommerce.model.User;
import com.application.ecommerce.model.Wishlist;

import java.util.List;

public interface WishlistService {

    void createWishlist(Wishlist wishlist);
    List<Wishlist> readWishlist(User user);
}
