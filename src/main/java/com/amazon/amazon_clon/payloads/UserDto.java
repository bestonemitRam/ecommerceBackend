package com.amazon.amazon_clon.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
    private Long userId;
    private String firstName;
    private String lastname;
    private String password;
    private String email;

}
