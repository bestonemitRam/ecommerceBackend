package com.amazon.amazon_clon.controllers;


import com.amazon.amazon_clon.config.AppConstant;
import com.amazon.amazon_clon.entites.Category;
import com.amazon.amazon_clon.entites.SubCategory;
import com.amazon.amazon_clon.entites.SuperSubCategory;
import com.amazon.amazon_clon.exceptions.APIException;
import com.amazon.amazon_clon.payloads.CategoryDTO;
import com.amazon.amazon_clon.payloads.SubcategoryDTO;
import com.amazon.amazon_clon.payloads.SuperSubCategoryDTO;
import com.amazon.amazon_clon.response.ResponseApi;
import com.amazon.amazon_clon.serviceInterface.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MainController {

    @Autowired
    CategoryService service;

    ResponseApi responseApi = new ResponseApi();

    @PostMapping("category")
    public ResponseEntity<ResponseApi> createCategory(@Valid @RequestBody(required = false) List<Category> category) {
        ResponseApi responseApi = new ResponseApi();
        if (category.isEmpty()) {
            responseApi.setMessage(AppConstant.response);
            responseApi.setStatus(false);
            return new ResponseEntity<>(responseApi, HttpStatus.BAD_REQUEST);
        }
        List<CategoryDTO> categoryDTOList = service.createCategory(category);
        responseApi.setData(categoryDTOList);
        responseApi.setMessage(AppConstant.response);
        responseApi.setStatus(true);
        return new ResponseEntity<>(responseApi, HttpStatus.CREATED);
    }

    @PostMapping("subcategory/{id}")
    public ResponseEntity<ResponseApi> createSubCategory(@Valid @RequestBody(required = false) List<SubCategory> subcategory, @PathVariable("id") Long id) {
        ResponseApi responseApi = new ResponseApi();
        if (id == null) {
            responseApi.setMessage("CategoryId is required " + id);
            responseApi.setStatus(false);
            return new ResponseEntity<>(responseApi, HttpStatus.BAD_REQUEST);
        }
        if (subcategory.isEmpty()) {
            responseApi.setMessage(AppConstant.response);
            responseApi.setStatus(false);
            return new ResponseEntity<>(responseApi, HttpStatus.BAD_REQUEST);
        }
        List<SubcategoryDTO> categoryDTOList = service.createSubCategory(subcategory, id);
        responseApi.setData(categoryDTOList);
        responseApi.setMessage(AppConstant.response);
        responseApi.setStatus(true);
        return new ResponseEntity<>(responseApi, HttpStatus.CREATED);


    }

    @PostMapping("super/subcategory/{id}")
    public ResponseEntity<ResponseApi> createSuperSubCategory(@Valid @RequestBody(required = false) List<SuperSubCategory> subcategory, @PathVariable("id") Long id) {

        ResponseApi responseApi = new ResponseApi();
        if (subcategory.isEmpty())
        {
            responseApi.setMessage(AppConstant.response);
            responseApi.setStatus(false);
            return new ResponseEntity<>(responseApi, HttpStatus.BAD_REQUEST);
        }
        List<SuperSubCategoryDTO> categoryDTOList = service.createSuperSubCategory(subcategory, id);
        responseApi.setData(categoryDTOList);
        responseApi.setMessage(AppConstant.response);
        responseApi.setStatus(true);

        return new ResponseEntity<>(responseApi, HttpStatus.CREATED);
    }

    @GetMapping("/category")
    public ResponseEntity<ResponseApi> getAllCategory()
    {

        List<CategoryDTO> categoryDTOList = service.getAllCategory();

        if (categoryDTOList.isEmpty())
        {
            responseApi.setStatus(false);
            responseApi.setMessage("record not found");
            responseApi.setData(categoryDTOList);
        }
        responseApi.setStatus(true);
        responseApi.setMessage("Successfully found data");
        responseApi.setData(categoryDTOList);
        return new ResponseEntity<>(responseApi, HttpStatus.OK);

    }

    @GetMapping("/subCategory/{id}")
    public ResponseEntity<ResponseApi> getAllSubCategory(@PathVariable(required = false) Long id) throws IOException
    {
        if (id == null)
        {
            throw new APIException("Category ID is required");
        }
        List<SubcategoryDTO> categoryDTOList = service.getAllSubCategory(id);
        if (categoryDTOList.isEmpty())
        {
            responseApi.setStatus(false);
            responseApi.setMessage("record not found");
            responseApi.setData(categoryDTOList);
            return new ResponseEntity<>(responseApi, HttpStatus.NOT_FOUND);
        }
        responseApi.setStatus(true);
        responseApi.setMessage("Successfully found data");
        responseApi.setData(categoryDTOList);
        return new ResponseEntity<>(responseApi, HttpStatus.OK);

    }

    @GetMapping("/SuperSubCategory/{id}")
    public ResponseEntity<ResponseApi> getAllSuperSubCategory(@PathVariable(required = false) Long id) throws IOException
    {
        if (id == null) {
            throw new APIException("SubCategory ID is required");
        }
        List<SuperSubCategoryDTO> categoryDTOList = service.getAllSuperSubCategory(id);

        if (categoryDTOList.isEmpty()) {
            responseApi.setStatus(false);
            responseApi.setMessage("record not found");
            responseApi.setData(categoryDTOList);
            return new ResponseEntity<>(responseApi, HttpStatus.NOT_FOUND);
        }
        responseApi.setStatus(true);
        responseApi.setMessage("Successfully found data");
        responseApi.setData(categoryDTOList);
        return new ResponseEntity<>(responseApi, HttpStatus.OK);

    }

}
