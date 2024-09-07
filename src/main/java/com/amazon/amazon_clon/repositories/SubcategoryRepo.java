package com.amazon.amazon_clon.repositories;

import com.amazon.amazon_clon.entites.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubcategoryRepo extends JpaRepository<SubCategory ,Long>
{
    List<SubCategory> findByCategoryId(Long categoryId);
}
