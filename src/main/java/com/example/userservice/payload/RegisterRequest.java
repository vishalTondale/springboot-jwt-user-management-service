package com.example.userservice.payload;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
}
