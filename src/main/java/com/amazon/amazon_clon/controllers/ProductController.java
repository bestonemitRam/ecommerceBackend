package com.amazon.amazon_clon.controllers;


import com.amazon.amazon_clon.config.AppConstant;
import com.amazon.amazon_clon.entites.Product;
import com.amazon.amazon_clon.entites.SuperSubCategory;
import com.amazon.amazon_clon.exceptions.APIException;
import com.amazon.amazon_clon.payloads.ProductDto;
import com.amazon.amazon_clon.payloads.SuperSubCategoryDTO;
import com.amazon.amazon_clon.repositories.ProductRepo;
import com.amazon.amazon_clon.request.ProductRequest;
import com.amazon.amazon_clon.response.ProductResponse;
import com.amazon.amazon_clon.response.ResponseApi;
import com.amazon.amazon_clon.serviceInterface.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ProductController {


    @Autowired
    ProductService productService;


    @PostMapping("product/{id}")
    public ResponseEntity<ResponseApi> createProduct(@Valid @RequestBody(required = false) List<ProductRequest> productRequests, @PathVariable("id") Long id) {
        ResponseApi responseApi = new ResponseApi();

        if (productRequests.isEmpty()) {
            responseApi.setMessage(AppConstant.response);
            responseApi.setStatus(false);
            return new ResponseEntity<>(responseApi, HttpStatus.BAD_REQUEST);
        }
        List<ProductDto> categoryDTOList = productService.createProduct(productRequests, id);
        responseApi.setData(categoryDTOList);
        responseApi.setMessage(AppConstant.response);
        responseApi.setStatus(true);
        return new ResponseEntity<>(responseApi, HttpStatus.CREATED);


    }


    @GetMapping("getAllProduct/{id}")
    public ResponseEntity<ProductResponse> getAllProduct(@PathVariable(value = "id", required = false) Long id) {
        List<ProductDto> productDtoList = productService.getAllProduct(id);
        ProductResponse responseApi = new ProductResponse();

        if (productDtoList.isEmpty())
        {
            responseApi.setData(productDtoList);
            responseApi.setMessage("record not found");
            responseApi.setStatus(false);
            return new ResponseEntity<>(responseApi, HttpStatus.OK);
        }
        responseApi.setData(productDtoList);
        responseApi.setMessage("record found");
        responseApi.setSuperSubCategoryApi(id);
        responseApi.setStatus(true);
        return new ResponseEntity<>(responseApi, HttpStatus.OK);
    }


}
