package com.springapp.firstapp.controller;

import com.springapp.firstapp.dto.BasketRequest;
import com.springapp.firstapp.module.Basket;
import com.springapp.firstapp.service.BasketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/basket")

@AllArgsConstructor
public class BasketController {
private final BasketService basketService;
@PostMapping("")
    public Basket createBasket(@RequestBody BasketRequest basketRequest){
    return basketService.createBasket(basketRequest);
}
@DeleteMapping("/{id}")
    public void deleteBasketById(@PathVariable Long id){
    basketService.deleteBasket(id);
}
@PutMapping("")
    public Basket updateBasket(@PathVariable Basket basket){
    return basketService.updateBasket(basket);
}
@GetMapping("/{id}")
    public Optional<Basket> getBasketById(@PathVariable Long id){
    return basketService.getBasketById(id);
}
@GetMapping("")
    public List<Basket> getAllBaskets(){
    return basketService.getAllBasket();
}
}