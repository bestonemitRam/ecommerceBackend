package com.amazon.amazon_clon.repositories;

import com.amazon.amazon_clon.entites.SubCategory;
import com.amazon.amazon_clon.entites.SuperSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SuperSubCategoryRepo extends JpaRepository<SuperSubCategory, Long> {
    List<SuperSubCategory> findBySubCategoryId(Long subCategoryId);
}
