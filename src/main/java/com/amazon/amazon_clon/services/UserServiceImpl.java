package com.amazon.amazon_clon.services;


import com.amazon.amazon_clon.entites.User;
import com.amazon.amazon_clon.exceptions.APIException;
import com.amazon.amazon_clon.payloads.UserDto;
import com.amazon.amazon_clon.repositories.UserRepo;
import com.amazon.amazon_clon.serviceInterface.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class UserServiceImpl implements UserService {



    @Autowired
    private UserRepo userrepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto registerUser(UserDto userDto) {
     try
     {
            User user = modelMapper.map(userDto, User.class);
            User createUser = userrepo.save(user);
            return modelMapper.map(createUser, UserDto.class);
     }
     catch (DataIntegrityViolationException e)
     {
      throw new APIException("User already exists with emailId: " + userDto.getEmail());
     }
    }
}
