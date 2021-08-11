package com.springapp.firstapp.controller;

import com.springapp.firstapp.dto.BasketRequest;
import com.springapp.firstapp.module.Basket;
import com.springapp.firstapp.module.User;
import com.springapp.firstapp.repo.BasketRepo;
import com.springapp.firstapp.repo.UserRepo;
import com.springapp.firstapp.service.BasketService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/basket")
@CrossOrigin (origins = "*",allowedHeaders = "*")

@AllArgsConstructor
public class BasketController {
private final BasketService basketService;
private final UserRepo userRepo;
private final BasketRepo basketRepo;
//@PostMapping("")//valid
//    public Basket createBasket(@RequestBody BasketRequest basketRequest){
//    User user  = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    System.out.println(user);
//    return basketService.createBasket(basketRequest,user);
//}
@PostMapping("/createBasket")//valid
public Basket createBasket(){
    User user  = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return basketService.createBasket(user);
}
@DeleteMapping("")//valid
    public void deleteBasket(){
    User user  = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    basketService.deleteBasket(basketService.getBasketByUser(user).getId());
}

    @GetMapping ("/BasketByUser")//valid
    public Basket getBasketByUser(){

        User user  = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user);
        return basketService.getBasketByUser(user);}
    @GetMapping("/{id}")
    public Basket getBasketById(@PathVariable Long id){
    return basketRepo.findById(id).get();
    }


}