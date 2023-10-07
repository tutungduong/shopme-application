package com.application.ecommerce.controller;

import com.application.ecommerce.common.ApiResponse;
import com.application.ecommerce.dto.ProductDto;
import com.application.ecommerce.exceptions.AuthenticationFailException;
import com.application.ecommerce.model.Product;
import com.application.ecommerce.model.User;
import com.application.ecommerce.model.Wishlist;
import com.application.ecommerce.repository.ProductRepository;
import com.application.ecommerce.service.ProductService;
import com.application.ecommerce.service.TokenService;
import com.application.ecommerce.service.WishlistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("wishlist")

public class WishlistController {

    private WishlistService wishlistService;

    private TokenService tokenService;
    private ProductRepository productRepository;

    public WishlistController(WishlistService wishlistService,
                              TokenService tokenService,
                              ProductRepository productRepository) {
        this.wishlistService = wishlistService;
        this.tokenService = tokenService;
        this.productRepository = productRepository;
    }

    // save product as wishlist item
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addWishList(@RequestBody ProductDto productDto, @RequestParam("token") String token) throws AuthenticationFailException {
        // first authenticate if the token is valid
        tokenService.authenticate(token);
        // then fetch the user linked to the token
        User user = tokenService.getUser(token);

        // get the product from product repo
        Product product = productRepository.getById(productDto.getId());

        // save wish list
        Wishlist wishList = new Wishlist(user, product);
        wishlistService.createWishlist(wishList);

        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to wishlist"), HttpStatus.CREATED);
    }

    // get all wishlist item for a user
    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token) throws AuthenticationFailException {
        // first authenticate if the token is valid
        tokenService.authenticate(token);
        // then fetch the user linked to the token
        User user = tokenService.getUser(token);
        // first retrieve the wishlist items
        List<Wishlist> wishLists = wishlistService.readWishlist(user);

        List<ProductDto> products = new ArrayList<>();
        for (Wishlist wishList : wishLists) {
            // change each product to product DTO
            products.add(getProductDto(wishList.getProduct()));
        }
        // send the response to user
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    public ProductDto getProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setDescription(product.getDescription());
        productDto.setImageURL(product.getImageUrl());
        productDto.setName(product.getName());
        productDto.setCategoryId(product.getId());
        productDto.setPrice(product.getPrice());
        productDto.setId(product.getId());
        return productDto;
    }
}
