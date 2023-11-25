package com.application.ecommerce.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    // List of each item
    private List<CartItemDto> cartItems;
    // total cost for the cart
    private double totalCost;

}
