package com.springapp.firstapp.controller;

import com.springapp.firstapp.MyUserDetailsService;
import com.springapp.firstapp.Util.JwtUtil;
import com.springapp.firstapp.dto.UserSignupRequest;
import com.springapp.firstapp.models.AuthenticationRequest;
import com.springapp.firstapp.models.AuthenticationResponse;
import com.springapp.firstapp.module.User;
import com.springapp.firstapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService ;
    private final MyUserDetailsService userDetailsService;
    private  final JwtUtil jwtTokenUtil;

    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)throws Exception{
        try { authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),authenticationRequest.getPassword())
        );
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password",e );
        }
        final UserDetails userDetails= userDetailsService
                .loadUserByUsername(authenticationRequest.getUserName());
        final String jwt=jwtTokenUtil.generateToken(userDetails);
        return new  ResponseEntity<>(new AuthenticationResponse(jwt,userDetails), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public User signUp(@RequestBody UserSignupRequest userSignupRequest){
        return  userService.createUser(userSignupRequest);
    }


}
