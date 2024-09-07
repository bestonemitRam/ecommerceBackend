package com.amazon.amazon_clon.services;

import com.amazon.amazon_clon.entites.Category;
import com.amazon.amazon_clon.entites.Product;
import com.amazon.amazon_clon.entites.SubCategory;
import com.amazon.amazon_clon.entites.SuperSubCategory;
import com.amazon.amazon_clon.exceptions.APIException;
import com.amazon.amazon_clon.exceptions.ResourceNotFoundException;
import com.amazon.amazon_clon.payloads.CategoryDTO;
import com.amazon.amazon_clon.payloads.ProductDto;
import com.amazon.amazon_clon.payloads.SubcategoryDTO;
import com.amazon.amazon_clon.payloads.SuperSubCategoryDTO;
import com.amazon.amazon_clon.repositories.CategoryRepo;
import com.amazon.amazon_clon.repositories.SubcategoryRepo;
import com.amazon.amazon_clon.repositories.SuperSubCategoryRepo;
import com.amazon.amazon_clon.serviceInterface.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    SubcategoryRepo subcategoryRepo;

    @Autowired
    SuperSubCategoryRepo superSubCategoryRepo;

    @Override
    public List<CategoryDTO> createCategory(List<Category> category) throws APIException {

        List<Category> categoryList = categoryRepo.saveAll(category);
        return categoryList.stream().map(category1 -> modelMapper.map(category1, CategoryDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<SubcategoryDTO> createSubCategory(List<SubCategory> subCategories, Long categoryId) {


        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new APIException("Category not found with id: " + categoryId));

        // Associate each SubCategory with the found Category
        for (SubCategory subCategory : subCategories) {
            subCategory.setCategory(category);
        }
        List<SubCategory> subCategories1 = subcategoryRepo.saveAll(subCategories);

        return subCategories1.stream().map(category1 -> modelMapper.map(category1, SubcategoryDTO.class)).collect(Collectors.toList());

    }


    @Override
    public List<SuperSubCategoryDTO> createSuperSubCategory(List<SuperSubCategory> superSubCategories,Long subCategoryId)
    {


        SubCategory subcategory = subcategoryRepo.findById(subCategoryId).orElseThrow(() -> new APIException("Category not found with id: " + subCategoryId));

       // Associate each SubCategory with the found Category
        for (SuperSubCategory subCategory : superSubCategories) {
            subCategory.setSubCategory(subcategory);
        }

        List<SuperSubCategory> superSubCategories1 = superSubCategoryRepo.saveAll(superSubCategories);

        return superSubCategories1.stream().map(category1 -> modelMapper.map(category1, SuperSubCategoryDTO.class)).collect(Collectors.toList());

    }

    @Override
    public List<CategoryDTO> getAllCategory()
    {
        List<Category> categoryList= categoryRepo.findAll();
        return  categoryList.stream().map(category1 -> modelMapper.map(category1, CategoryDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<SubcategoryDTO> getAllSubCategory(Long categoryId) {


        List<SubCategory> categoryList= subcategoryRepo.findByCategoryId(categoryId);

        return  categoryList.stream().map(category1 -> modelMapper.map(category1, SubcategoryDTO.class)).collect(Collectors.toList());

    }

    @Override
    public List<SuperSubCategoryDTO> getAllSuperSubCategory(Long id)
    {
        List<SuperSubCategory> superSubCategories= superSubCategoryRepo.findBySubCategoryId(id);

        return  superSubCategories.stream().map(category1 -> modelMapper.map(category1, SuperSubCategoryDTO.class)).collect(Collectors.toList());

    }


}
