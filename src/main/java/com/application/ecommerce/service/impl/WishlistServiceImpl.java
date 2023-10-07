package com.application.ecommerce.service.impl;

import com.application.ecommerce.model.User;
import com.application.ecommerce.model.Wishlist;
import com.application.ecommerce.repository.WishlistRepository;
import com.application.ecommerce.service.WishlistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {

    private WishlistRepository wishlistRepository;

    public WishlistServiceImpl(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }


    @Override
    public void createWishlist(Wishlist wishlist) {
        wishlistRepository.save(wishlist);
    }

    @Override
    public List<Wishlist> readWishlist(User user) {
        return wishlistRepository.findAllByUserOrderByCreatedDateDesc(user);
    }
}
