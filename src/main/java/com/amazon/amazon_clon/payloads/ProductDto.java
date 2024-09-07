package com.amazon.amazon_clon.payloads;

import com.amazon.amazon_clon.entites.ProductImage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {

    private String productName;
    private Long id;
    private Long super_sub_catId;

    private String description;
    private Integer quantity;
    private double price;
    private double discount;
    private double specialPrice;
    private List<ProductImageDTO> images;
}
