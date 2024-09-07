package com.amazon.amazon_clon.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse<T>
{
    private String message;
    private boolean status;
    private Long superSubCategoryApi;
    private T data;
}
