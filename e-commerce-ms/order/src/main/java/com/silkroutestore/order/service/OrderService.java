package com.silkroutestore.order.service;

import com.silkroutestore.order.model.OrderRequest;
import com.silkroutestore.order.model.OrderResponse;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(long orderId);
}
