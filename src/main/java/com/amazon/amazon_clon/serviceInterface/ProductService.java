package com.amazon.amazon_clon.serviceInterface;

import com.amazon.amazon_clon.entites.Product;
import com.amazon.amazon_clon.entites.ProductImage;
import com.amazon.amazon_clon.payloads.ProductDto;
import com.amazon.amazon_clon.payloads.ProductImageDTO;
import com.amazon.amazon_clon.request.ProductRequest;

import java.util.List;

public interface ProductService {

    public List<ProductDto> createProduct(List<ProductRequest> products, Long superSubcategoryId);

    public List<ProductDto> getAllProduct(Long id);




}
