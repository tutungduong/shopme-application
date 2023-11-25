package com.application.ecommerce.controller;

import com.application.ecommerce.common.ApiResponse;
import com.application.ecommerce.dto.checkout.CheckoutItemDto;
import com.application.ecommerce.dto.checkout.StripeResponse;
import com.application.ecommerce.exceptions.AuthenticationFailException;
import com.application.ecommerce.exceptions.OrderNotFoundException;
import com.application.ecommerce.model.Order;
import com.application.ecommerce.model.User;
import com.application.ecommerce.service.OrderService;
import com.application.ecommerce.service.AuthenticationService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.stripe.model.checkout.Session;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private AuthenticationService authenticationService;


    // stripe create session API
    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {
        // create the stripe session
        Session  session = orderService.createSession(checkoutItemDtoList);
        StripeResponse stripeResponse = new StripeResponse(session.getId());
        // send the stripe session id in response
        return new ResponseEntity<StripeResponse>(stripeResponse, HttpStatus.OK);
    }

    // place order after checkout
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> placeOrder(@RequestParam("token") String token, @RequestParam("sessionId") String sessionId)
            throws AuthenticationFailException {
        // validate token
        authenticationService.authenticate(token);
        // retrieve user
        User user = authenticationService.getUser(token);
        // place the order
        orderService.placeOrder(user, sessionId);
        return new ResponseEntity<>(new ApiResponse(true, "Order has been placed"), HttpStatus.CREATED);
    }

    // get all orders
    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrders(@RequestParam("token") String token) throws AuthenticationFailException {
        // validate token
        authenticationService.authenticate(token);
        // retrieve user
        User user = authenticationService.getUser(token);
        // get orders
        List<Order> orderDtoList = orderService.listOrders(user);

        return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
    }


    // get orderitems for an order
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderById(@PathVariable("id") Integer id, @RequestParam("token") String token)
            throws AuthenticationFailException, OrderNotFoundException {
        // 1. validate token

        // validate token
        authenticationService.authenticate(token);

        // 2. find user

        // retrieve user
        User user = authenticationService.getUser(token);


        // 3. call getOrder method of order service an pass orderId and user

        Order order = orderService.getOrder(id, user);

        // 4. display order in json response

        return new ResponseEntity<>(order, HttpStatus.OK);

    }

}