package com.application.ecommerce.service;

import com.application.ecommerce.dto.checkout.CheckoutItemDto;
import com.application.ecommerce.exceptions.OrderNotFoundException;
import com.application.ecommerce.model.Order;
import com.application.ecommerce.model.User;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import java.util.List;

public interface OrderService {

    void placeOrder(User user, String sessionId);
    List<Order> listOrders(User user);
    Order getOrder(Integer orderId, User user) throws OrderNotFoundException;

    Session createSession(List<CheckoutItemDto> checkoutItemDtoList) throws StripeException;
}
