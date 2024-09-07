package com.amazon.amazon_clon.serviceInterface;

import com.amazon.amazon_clon.entites.Cart;
import com.amazon.amazon_clon.payloads.CartDTO;

public interface CartService {

    public CartDTO addToCart(Long productId, int quantity, Long carId);

}
