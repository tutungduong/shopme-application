package com.application.ecommerce.controller;


import com.application.ecommerce.common.ApiResponse;
import com.application.ecommerce.dto.AddToCartDto;
import com.application.ecommerce.dto.CartDto;
import com.application.ecommerce.exceptions.AuthenticationFailException;
import com.application.ecommerce.exceptions.CartItemNotExistException;
import com.application.ecommerce.exceptions.ProductNotExistException;
import com.application.ecommerce.model.Product;
import com.application.ecommerce.model.User;
import com.application.ecommerce.service.CartService;
import com.application.ecommerce.service.CategoryService;
import com.application.ecommerce.service.ProductService;
import com.application.ecommerce.service.TokenService;
import org.antlr.v4.runtime.Token;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {


    private CartService cartService;
    private CategoryService categoryService;
    private ProductService productService;
    private TokenService tokenService;

    public CartController(CategoryService categoryService,
                          ProductService productService,
                          TokenService tokenService,
                          CartService cartService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.tokenService = tokenService;
        this.cartService = cartService;
    }
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
                                                 @RequestParam("token") String token) throws AuthenticationFailException,
                                                    ProductNotExistException, AuthenticationFailException{
        // first authenticate the token
        tokenService.authenticate(token);

        // get the user
        User user = tokenService.getUser(token);

        // find the product to add and add item by service

        Product product =productService.getProductById(addToCartDto.getProductId());
        cartService.addToCart(addToCartDto, product, user);

        // return response
        return new ResponseEntity<>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token")String token) throws AuthenticationFailException{
        // first authenticate the token
        tokenService.authenticate(token);

        // get the user
        User user = tokenService.getUser(token);

        // get items in the cart for the user

        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<CartDto>(cartDto,HttpStatus.OK);
    }
    // task delete cart item
    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") int cartItemId,
                                                      @RequestParam("token") String token)
            throws AuthenticationFailException, CartItemNotExistException {
        tokenService.authenticate(token);
        User user = tokenService.getUser(token);
        // method to be completed
        cartService.deleteCartItem(cartItemId, user);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
    }
}
