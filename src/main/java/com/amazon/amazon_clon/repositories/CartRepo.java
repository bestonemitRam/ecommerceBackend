package com.amazon.amazon_clon.repositories;

import com.amazon.amazon_clon.entites.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart,Long>
{
}
