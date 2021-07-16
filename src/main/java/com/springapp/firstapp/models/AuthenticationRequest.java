package com.springapp.firstapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//input
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationRequest {
    private String userName;
    private String password;
}
