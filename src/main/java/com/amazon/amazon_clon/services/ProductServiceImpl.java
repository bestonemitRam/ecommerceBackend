package com.amazon.amazon_clon.services;

import com.amazon.amazon_clon.entites.Product;
import com.amazon.amazon_clon.entites.ProductImage;
import com.amazon.amazon_clon.entites.SuperSubCategory;
import com.amazon.amazon_clon.exceptions.APIException;
import com.amazon.amazon_clon.payloads.ProductDto;
import com.amazon.amazon_clon.payloads.ProductImageDTO;
import com.amazon.amazon_clon.payloads.SuperSubCategoryDTO;
import com.amazon.amazon_clon.repositories.ProductRepo;
import com.amazon.amazon_clon.repositories.SuperSubCategoryRepo;
import com.amazon.amazon_clon.request.ProductRequest;
import com.amazon.amazon_clon.serviceInterface.ProductService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    ProductRepo productRepo;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    SuperSubCategoryRepo superSubCategoryRepo;





    @Override
    public List<ProductDto> createProduct(List<ProductRequest> productRequest, Long superSubcategoryId) {

        SuperSubCategory superSubCategory = superSubCategoryRepo.findById(superSubcategoryId).orElseThrow(() -> new APIException("Category not found with id: " + superSubcategoryId));





        List<Product> products = productRequest.stream().map(request ->
        {

            Optional<Product> existingProduct = productRepo.findByProductName(request.getProductName());
            if (existingProduct.isPresent())
            {
                throw new APIException("Product already exists with name: " + request.getProductName());
            }

            Product product = new Product();
            product.setProductName(request.getProductName());
            product.setPrice(request.getPrice());
            product.setPrice(request.getPrice());
            product.setDescription(request.getDescription());
            product.setQuantity(request.getQuantity());
            product.setDiscount(request.getDiscount());
            product.setSpecialPrice(request.getSpecialPrice());


            List<ProductImage> images = request.getImagesUrl().stream().map(url -> {
                ProductImage image = new ProductImage();
                image.setImages(url);
                image.setProduct(product);  // Link image to product
                return image;
            }).collect(Collectors.toList());

            product.setImages(images);  // Set images in the product

            return product;
        }).collect(Collectors.toList());




        for (Product product : products)
        {
            product.setSuperSubCategory(superSubCategory);
         }productRepo.saveAll(products);
        List<Product> product = productRepo.saveAll(products);

        return product.stream().map(products1 -> modelMapper.map(products1, ProductDto.class)).collect(Collectors.toList());



    }

    @Override
    public List<ProductDto> getAllProduct(Long id)
    {
           List<Product> productList= productRepo.findBySuperSubCategoryId(id);

         return  productList.stream().map(products1 -> modelMapper.map(products1, ProductDto.class)).collect(Collectors.toList());
    }


}
