package com.silkroutestore.product.service;

import com.silkroutestore.product.model.ProductRequest;
import com.silkroutestore.product.model.ProductResponse;

public interface ProductService {
    Long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(Long productId);

    void reduceQuantity(long productId, long quantity);
}
