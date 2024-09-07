package com.amazon.amazon_clon.entites;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User
{
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long userId;


     private String firstName;
     private String lastName;
     private String password;

     @Email
     @Column(unique = true, nullable = false)
     private String email;

}
