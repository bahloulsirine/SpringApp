package com.springapp.firstapp.controller;


import com.springapp.firstapp.module.User;
import com.springapp.firstapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping("")
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
         userService.deleteUser(id);
    }

    @PutMapping("")
    public User updateUser(@RequestBody User user)
    {
        return userService.updateUser(user);
    }
    @GetMapping("firstname/{firstname}")
    public List<User> getUsersByFirstName(@PathVariable String firstname){
        return userService.getUsersByFirstname(firstname);
    }
    @GetMapping("lastname/{lastname}")
    public List<User> getUsersByLastName(@PathVariable String lastname){
        return userService.getUsersByLastname(lastname);
    }
    @GetMapping("address/{address}")
    public List<User> getUsersByAddress(@PathVariable String address){
        return userService.getUsersByAddress(address);}
}
