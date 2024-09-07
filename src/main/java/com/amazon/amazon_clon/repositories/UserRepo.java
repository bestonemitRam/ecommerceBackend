package com.amazon.amazon_clon.repositories;


import com.amazon.amazon_clon.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

}
