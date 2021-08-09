package com.springapp.firstapp.models;

import com.springapp.firstapp.module.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationResponse {
    private  String jwt ;
    private UserDetails user ;
}
