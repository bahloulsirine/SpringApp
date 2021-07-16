package com.springapp.firstapp.controller;

import com.springapp.firstapp.dto.BasketRequest;
import com.springapp.firstapp.module.Basket;
import com.springapp.firstapp.module.User;
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

@AllArgsConstructor
public class BasketController {
private final BasketService basketService;
private final UserRepo userRepo;
@PostMapping("")//valid
    public Basket createBasket(@RequestBody BasketRequest basketRequest){
    String mail  = SecurityContextHolder.getContext().getAuthentication().getName();
    User user=userRepo.getUserByEmail(mail);
    return basketService.createBasket(basketRequest,user);
}
@DeleteMapping("/{id}")//valid
    public void deleteBasketById(@PathVariable Long id){
    basketService.deleteBasket(id);
}

@GetMapping("/{id}")//valid
    public Optional<Basket> getBasketById(@PathVariable Long id){
    return basketService.getBasketById(id);
}

@PreAuthorize("has_role(ADMIN)")
@GetMapping("")//valid
    public List<Basket> getAllBaskets(){
    return basketService.getAllBasket();
}

    @GetMapping ("/BasketByuser")//valid
    public Basket getBasketByUser(){
        String mail  = SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepo.getUserByEmail(mail);
        return basketService.getBasketByUser(user);}

}