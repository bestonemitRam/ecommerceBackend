package com.amazon.amazon_clon.repositories;

import com.amazon.amazon_clon.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long>
{
    Optional<Product> findByProductName(String productName);
    List<Product> findBySuperSubCategoryId(Long id);

}
