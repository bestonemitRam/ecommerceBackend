package com.amazon.amazon_clon.controllers;



import com.amazon.amazon_clon.config.AppConstant;
import com.amazon.amazon_clon.exceptions.UserNotFoundException;
import com.amazon.amazon_clon.payloads.UserDto;
import com.amazon.amazon_clon.response.ResponseApi;
import com.amazon.amazon_clon.serviceInterface.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController
{
    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<ResponseApi> createUser(@Valid
                                                      @RequestBody UserDto user)  throws UserNotFoundException
    {
        UserDto  userDto=userService.registerUser(user);
        ResponseApi userResponse=new ResponseApi();
        userResponse.setStatus(true);
        userResponse.setMessage(AppConstant.response);
        userResponse.setData(userDto);
        return new ResponseEntity<ResponseApi>(userResponse,HttpStatus.CREATED);
 }
}
