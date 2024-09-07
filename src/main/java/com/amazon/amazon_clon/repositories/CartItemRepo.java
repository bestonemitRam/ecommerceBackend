package com.amazon.amazon_clon.repositories;

import com.amazon.amazon_clon.entites.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepo extends JpaRepository<CartItem,Long> {
  CartItem findCartItemByProductIdAndCartId(Long cartId,Long productId);
}
