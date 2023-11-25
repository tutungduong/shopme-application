package com.application.ecommerce.service.impl;

import com.application.ecommerce.model.User;
import com.application.ecommerce.model.WishList;
import com.application.ecommerce.repository.WishListRepository;
import com.application.ecommerce.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListServiceImpl implements WishListService {

    @Autowired
    private WishListRepository wishListRepository;

    @Override
    public void createWishlist(WishList wishList) {
        wishListRepository.save(wishList);
    }
    @Override
    public List<WishList> readWishList(User user) {
        return wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
    }
}
