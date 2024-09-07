package com.amazon.amazon_clon.controllers;


import com.amazon.amazon_clon.entites.Cart;
import com.amazon.amazon_clon.payloads.CartDTO;
import com.amazon.amazon_clon.serviceInterface.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class CartController
{


    @Autowired
    CartService cartService;

    @PostMapping("addToCart")
    public ResponseEntity<CartDTO>  addToCart(
            @RequestParam(required = false) Long cartId,
            @RequestParam Long productId,
            @RequestParam Integer quentity )
    {




        CartDTO cart=cartService.addToCart(productId,quentity,cartId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
