package com.application.ecommerce.service;

import com.application.ecommerce.model.User;
import com.application.ecommerce.model.WishList;

import java.util.List;

public interface WishListService {

    void createWishlist(WishList wishList);
    List<WishList> readWishList(User user);
}
