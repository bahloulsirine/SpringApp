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

    @PostMapping("")//valid
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")//valid
    public Optional<User> getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping("")//valid
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }

    @DeleteMapping("/{id}")//valid
    public void deleteUser(@PathVariable Long id){
         userService.deleteUser(id);
    }

    @PutMapping("")//valid
    public User updateUser(@RequestBody User user)
    {
        return userService.updateUser(user);
    }
    @GetMapping("firstname/{firstname}")//valid
    public List<User> getUsersByFirstName(@PathVariable String firstname){
        return userService.getUsersByFirstname(firstname);
    }
    @GetMapping("lastname/{lastname}")//valid
    public List<User> getUsersByLastName(@PathVariable String lastname){
        return userService.getUsersByLastname(lastname);
    }
    @GetMapping("address/{address}")//valid
    public List<User> getUsersByAddress(@PathVariable String address){
        return userService.getUsersByAddress(address);}

    @GetMapping("/UserWon/{orderTotal}")//valid
    public List<User>getWonUsers (@PathVariable int orderTotal){
    return userService.getWonUsers(orderTotal);}

    @PutMapping("/deleteTotalOrder")
    public  List<User>deleteTotalOrderForUsers(@RequestBody List<User> users){
        for (User user:users){
            user.setTotalOrder(0);
            userService.updateUser(user);
        }
        return  users;
    }

}