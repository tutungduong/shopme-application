package com.application.ecommerce.service.impl;

import com.application.ecommerce.dto.cart.AddToCartDto;
import com.application.ecommerce.dto.cart.CartDto;
import com.application.ecommerce.dto.cart.CartItemDto;
import com.application.ecommerce.exceptions.CustomException;
import com.application.ecommerce.model.Cart;
import com.application.ecommerce.model.Product;
import com.application.ecommerce.model.User;
import com.application.ecommerce.repository.CartRepository;
import com.application.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServerImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public void addToCart(AddToCartDto addToCartDto, Product product, User user) {
        Cart cart = new Cart(product, addToCartDto.getQuantity(), user);
        cartRepository.save(cart);
    }
    @Override
    public CartDto listCartItems(User user) {
        // first get all the cart items for user
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);

        // convert cart to cartItemDto
        List<CartItemDto> cartItems = new ArrayList<>();
        for (Cart cart:cartList){
            CartItemDto cartItemDto = new CartItemDto(cart);
            cartItems.add(cartItemDto);
        }

        // calculate the total price
        double totalCost = 0;
        for (CartItemDto cartItemDto :cartItems){
            totalCost += cartItemDto.getProduct().getPrice() * cartItemDto.getQuantity();
        }

        // return cart DTO
        return new CartDto(cartItems,totalCost);
    }
    @Override
    public void deleteUserCartItems(User user) {
        cartRepository.deleteByUser(user);
    }

}
