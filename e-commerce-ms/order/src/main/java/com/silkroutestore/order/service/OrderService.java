package com.silkroutestore.order.service;

import com.silkroutestore.order.model.OrderRequest;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);
}
