package com.amazon.amazon_clon.repositories;

import com.amazon.amazon_clon.entites.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepo extends JpaRepository<ProductImage ,Long>
{
}
