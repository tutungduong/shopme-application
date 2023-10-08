package com.application.ecommerce.service.impl;

import com.application.ecommerce.dto.AddToCartDto;
import com.application.ecommerce.dto.CartDto;
import com.application.ecommerce.dto.CartItemDto;
import com.application.ecommerce.exceptions.CustomException;
import com.application.ecommerce.model.Cart;
import com.application.ecommerce.model.Product;
import com.application.ecommerce.model.User;
import com.application.ecommerce.repository.CartRepository;
import com.application.ecommerce.service.CartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServerImpl implements CartService {

   private CartRepository cartRepository;

    public CartServerImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

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
        for(Cart cart : cartList){
            CartItemDto cartItemDto = new CartItemDto(cart);
            cartItems.add(cartItemDto);
        }

        // calculate the total price
        double totalCost =0;
        for(CartItemDto cartItemDto: cartItems){
            totalCost += cartItemDto.getProduct().getPrice() * cartItemDto.getQuantity();
        }

        // return cart DTO

        return new CartDto(cartItems, totalCost);
    }

    @Override
    public void deleteCartItem(Integer cartItemId, User user) {
        Optional<Cart> optionalCart = cartRepository.findById(cartItemId);
        if(!optionalCart.isPresent()){
            throw new CustomException("cart item id is invalid: " + cartItemId);
        }
        Cart cart = optionalCart.get();
        if(cart.getUser() != user){
           throw new CustomException("cart item does not belong to user: " + cartItemId);
        }
        cartRepository.delete(cart);
    }

}
