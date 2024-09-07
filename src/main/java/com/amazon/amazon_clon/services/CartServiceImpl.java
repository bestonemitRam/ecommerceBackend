package com.amazon.amazon_clon.services;

import com.amazon.amazon_clon.entites.Cart;
import com.amazon.amazon_clon.entites.CartItem;
import com.amazon.amazon_clon.entites.Product;
import com.amazon.amazon_clon.exceptions.APIException;
import com.amazon.amazon_clon.payloads.CartDTO;
import com.amazon.amazon_clon.payloads.ProductDto;
import com.amazon.amazon_clon.repositories.CartItemRepo;
import com.amazon.amazon_clon.repositories.ProductRepo;
import com.amazon.amazon_clon.repositories.CartRepo;
import com.amazon.amazon_clon.serviceInterface.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CartServiceImpl implements CartService {

   @Autowired
    private CartRepo cartRepository;

    @Autowired
    private ProductRepo productRepository;

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CartDTO addToCart(Long productId, int quantity,Long cartId)
     {



         Cart cart;

         // Check if a cart already exists, otherwise create a new one
         if (cartId != null)
         {
             cart = cartRepository.findById(cartId)
                     .orElseThrow(() -> new APIException("Cart cartId"+ cartId));
         } else {
             // Create a new cart if no cartId is provided
             cart = new Cart();
             cart.setTotalPrice(0.0);
             cartRepository.save(cart);  // Save the cart to generate its ID
         }

         // Initialize cartItems if null
         if (cart.getCartItems() == null)
         {
             cart.setCartItems(new ArrayList<>());
         }


         Product product = productRepository.findById(productId).orElseThrow(() -> new APIException("Product not found with id: " + productId));
         Cart newCart = new Cart();

         CartItem cartItem = cartItemRepo.findCartItemByProductIdAndCartId(cart.getId(), productId);
         if (cartItem != null) {
             throw new APIException("Product " + product.getProductName() + " already exists in the cart");
         }

         // Check product availability
         if (product.getQuantity() == 0) {
             throw new APIException(product.getProductName() + " is not available");
         }

         if (product.getQuantity() < quantity) {
             throw new APIException("Please, make an order of " + product.getProductName()
                     + " less than or equal to the available quantity of " + product.getQuantity() + ".");
         }
         CartItem newCartItem = new CartItem();
         newCartItem.setProduct(product);
         newCartItem.setCart(cart);
         newCartItem.setQuantity(quantity);
         cart.setTotalPrice(cart.getTotalPrice() + (product.getSpecialPrice() * quantity));


         cartItemRepo.save(newCartItem);
         // Save the updated cart
         cartRepository.save(cart);

         CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

         // Map the products in the cart to DTOs for the response
         List<ProductDto> productDTOs = cart.getCartItems().stream()
                 .map(p -> modelMapper.map(p.getProduct(), ProductDto.class))
                 .collect(Collectors.toList());

         cartDTO.setProducts(productDTOs);


      // Check if the cart already contains this product

//         Optional<CartItem> existingCartItem = newCart.getCartItems().stream()
//                 .filter(item -> item.getProduct().getId().equals(productId))
//                 .findFirst();
//
//         if (existingCartItem.isPresent())
//         {
//             // Update the quantity of the existing cart item
//             CartItem cartItem = existingCartItem.get();
//             cartItem.setQuantity(cartItem.getQuantity() + quantity);
//             cartItemRepo.save(cartItem);
//         } else
//         {
//             // Add a new cart item
//             CartItem cartItem = new CartItem();
//             cartItem.setProduct(product);
//             cartItem.setQuantity(quantity);
//             cartItem.setCart(newCart);
//             newCart.getCartItems().add(cartItem);
//             cartItemRepo.save(cartItem);
//         }

         return cartDTO;


    }
}
