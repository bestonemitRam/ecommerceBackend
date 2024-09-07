package com.amazon.amazon_clon.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseApi<T> {

    private String message;

    private boolean status;
    private T data;
}
