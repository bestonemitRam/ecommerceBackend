package com.amazon.amazon_clon.serviceInterface;

import com.amazon.amazon_clon.entites.Category;
import com.amazon.amazon_clon.entites.Product;
import com.amazon.amazon_clon.entites.SubCategory;
import com.amazon.amazon_clon.entites.SuperSubCategory;
import com.amazon.amazon_clon.payloads.CategoryDTO;
import com.amazon.amazon_clon.payloads.ProductDto;
import com.amazon.amazon_clon.payloads.SubcategoryDTO;
import com.amazon.amazon_clon.payloads.SuperSubCategoryDTO;

import java.util.List;

public interface CategoryService
{

    public List<CategoryDTO> createCategory(List<Category> category);
    public List<SubcategoryDTO> createSubCategory(List<SubCategory> subcategory,Long categoryId);
    public List<SuperSubCategoryDTO> createSuperSubCategory(List<SuperSubCategory> superSubCategory,Long subCategoryId);
    public List<CategoryDTO> getAllCategory();
    public List<SubcategoryDTO> getAllSubCategory(Long id);
    public List<SuperSubCategoryDTO> getAllSuperSubCategory(Long id);



}
