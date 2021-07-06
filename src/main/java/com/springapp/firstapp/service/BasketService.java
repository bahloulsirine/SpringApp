package com.springapp.firstapp.service;

import com.springapp.firstapp.module.Basket;
import com.springapp.firstapp.repo.BasketRepo;
import com.springapp.firstapp.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BasketService {
    private final BasketRepo basketRepo;

    public Basket updateBasket(Basket basket){
        return basketRepo.save(basket);
    }
    public void deleteBasket(Long id){
        basketRepo.deleteById(id);
    }
    public Basket createBasket(Basket basket){
        return  basketRepo.save(basket);
    }

    public Optional<Basket> getBasketById(Long id){
        return basketRepo.findById(id);
    }

  public List<Basket> getAllBasket(){
        return basketRepo.findAll();
  }

}
