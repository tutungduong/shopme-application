package com.application.ecommerce.controller;

import com.application.ecommerce.common.ApiResponse;
import com.application.ecommerce.exceptions.AuthenticationFailException;
import com.application.ecommerce.exceptions.OrderNotFoundException;
import com.application.ecommerce.model.Order;
import com.application.ecommerce.model.User;
import com.application.ecommerce.service.OrderService;
import com.application.ecommerce.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private TokenService tokenService;
    private OrderService orderService;

    public OrderController(TokenService tokenService, OrderService orderService) {
        this.tokenService = tokenService;
        this.orderService = orderService;
    }

    // place order after checkout
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> placeOrder(@RequestParam("token") String token, @RequestParam("sessionId") String sessionId)
            throws AuthenticationFailException {
        // validate token
        tokenService.authenticate(token);
        // retrieve user
        User user = tokenService.getUser(token);
        // place the order
        orderService.placeOrder(user, sessionId);
        return new ResponseEntity<>(new ApiResponse(true, "Order has been placed"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrders(@RequestParam("token") String token) throws AuthenticationFailException {
        // validate token
        tokenService.authenticate(token);
        // retrieve user
        User user = tokenService.getUser(token);
        // get orders
        List<Order> orderDtoList = orderService.listOrders(user);

        return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
    }

    // get orderitems for an order
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderById(@PathVariable("id") Integer id, @RequestParam("token") String token)
            throws AuthenticationFailException, OrderNotFoundException {
        // 1. validate token
        tokenService.authenticate(token);
        // 2. find user
        User user = tokenService.getUser(token);
        // 3. call getOrder method of order service an pass orderId and user

        Order order = orderService.getOrder(id, user);

        // 4. display order in json response
        return  new ResponseEntity<>(order, HttpStatus.OK);
    }
}
