package com.springapp.firstapp.controller;


import com.springapp.firstapp.dto.UserUpdateRequest;
import com.springapp.firstapp.module.User;
import com.springapp.firstapp.repo.RoleRepo;
import com.springapp.firstapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
@CrossOrigin (origins = "*",allowedHeaders = "*")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final RoleRepo roleRepo;


    @GetMapping("/{id}")//valid
    public Optional<User> getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping("profile")
    public User getCurrentUser(){
      return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")//valid
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }


    @PutMapping("")//valid
    public User updateUser(@RequestBody UserUpdateRequest user)
    {
        return userService.updateUser(user);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("firstname/{firstname}")//valid
    public List<User> getUsersByFirstName(@PathVariable String firstname){
        return userService.getUsersByFirstname(firstname);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("lastname/{lastname}")//valid
    public List<User> getUsersByLastName(@PathVariable String lastname){
        return userService.getUsersByLastname(lastname);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("address/{address}")//valid
    public List<User> getUsersByAddress(@PathVariable String address){
        return userService.getUsersByAddress(address);}
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("cin/{cin}")
    public User getUserByCin(@PathVariable Long cin){
        return userService.getUserByCin(cin);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/UserWon/{orderTotal}")//valid
    public List<User>getWonUsers (@PathVariable int orderTotal){
    return userService.getWonUsers(orderTotal);}

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/deleteTotalOrder")//valid
    public  void deleteTotalOrderForUsers(@RequestBody List<Long > userIds){
        userService.resetUsersOrder(userIds);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/makeProvider/{id}")
    public User makeProvider(@PathVariable Long id){
        User user=getUserById(id).get();
        return userService.makeProvider(user);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/makeDelivery/{id}")
    public User makeDelivery(@PathVariable Long id){
        User user=getUserById(id).get();
        return userService.makeDelivery(user);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void notActiveAccount(@PathVariable Long id){
       userService.disableUserAccount(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getProviders")
    public List<User> getProviders(){
        return userService.getProviders();
    }

    @GetMapping("/verifyPassword/{password}")
    public Boolean comparePassword(@PathVariable String password){
        return userService.comparePassword(password);
    }
    @GetMapping("updatePassword/{password}")
    public User updatePassword(@PathVariable String password){
        return userService.updatePassword(password);
    }
}
//1->Admin,2-> User,3-> Provider,4-> Delivery