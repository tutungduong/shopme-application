package com.application.ecommerce.service;

import com.application.ecommerce.dto.cart.AddToCartDto;
import com.application.ecommerce.dto.cart.CartDto;
import com.application.ecommerce.model.Product;
import com.application.ecommerce.model.User;

public interface CartService {
    void addToCart(AddToCartDto addToCartDto, Product product, User user);

    CartDto listCartItems(User user);
    void deleteUserCartItems(User user);
}
