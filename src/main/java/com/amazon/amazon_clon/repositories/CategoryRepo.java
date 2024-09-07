package com.amazon.amazon_clon.repositories;

import com.amazon.amazon_clon.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long>
{
}
