package com.springapp.firstapp.controller;


import com.springapp.firstapp.module.User;
import com.springapp.firstapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

//    @PreAuthorize("has_role(ADMIN)")
//    @PostMapping("")//valid
//    public User createUser(@RequestBody User user) {
//        return userService.createUser(user);
//    }

    @PreAuthorize("has_role(ADMIN)")
    @GetMapping("/{id}")//valid
    public Optional<User> getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PreAuthorize("has_role(ADMIN)")
    @GetMapping("")//valid
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PreAuthorize("has_role(ADMIN)")
    @DeleteMapping("/{id}")//valid
    public void deleteUser(@PathVariable Long id){
         userService.deleteUser(id);
    }

    @PutMapping("")//valid
    public User updateUser(@RequestBody User user)
    {
        return userService.updateUser(user);
    }

    @PreAuthorize("has_role(ADMIN)")
    @GetMapping("firstname/{firstname}")//valid
    public List<User> getUsersByFirstName(@PathVariable String firstname){
        return userService.getUsersByFirstname(firstname);
    }
    @PreAuthorize("has_AnyRole(ROLE_ADMIN,ROLE_PROVIDER)")
    @GetMapping("lastname/{lastname}")//valid
    public List<User> getUsersByLastName(@PathVariable String lastname){
        return userService.getUsersByLastname(lastname);
    }
    @GetMapping("address/{address}")//valid
    public List<User> getUsersByAddress(@PathVariable String address){
        return userService.getUsersByAddress(address);}

    @PreAuthorize("has_role(ADMIN)")
    @GetMapping("/UserWon/{orderTotal}")//valid
    public List<User>getWonUsers (@PathVariable int orderTotal){
    return userService.getWonUsers(orderTotal);}

    @PreAuthorize("hasRole(ADMIN)")
    @PutMapping("/deleteTotalOrder")//valid
    public  void deleteTotalOrderForUsers(@RequestBody List<User> users){
       List<Long> userIds=new ArrayList<>();
        for (User user:users){
           userIds.add(user.getId());
        }

        userService.resetUsersOrder(userIds);

    }

}