package com.springapp.firstapp.controller;


import com.springapp.firstapp.dto.BasketArticleRequest;
import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.BasketArticle;
import com.springapp.firstapp.module.User;
import com.springapp.firstapp.repo.BasketArticleRepo;
import com.springapp.firstapp.service.BasketArticleService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/basketArticle")
@AllArgsConstructor
@CrossOrigin (origins = "*",allowedHeaders = "*")
public class BasketArticleController {
    private  final BasketArticleService basketArticleService;
    private final BasketArticleRepo basketArticleRepo;

    @PutMapping("")//valid
    public BasketArticle updateBasketArticle(@RequestBody BasketArticleRequest basketArticleRequest){
        return basketArticleService.update(basketArticleRequest);
    }
    @DeleteMapping("/{id}")//valid
    public void deleteBasketArticle(@PathVariable Long id){
        basketArticleService.deleteBasketArticle(id);
    }
    @GetMapping("/{id}")
    public BasketArticle getBasketArticle(@PathVariable Long id){
        return basketArticleRepo.findById(id).get();
    }
    @GetMapping("/byBasket/{id}")
    public List<BasketArticle> basketArticleByBasketId(@PathVariable Long id){
        return basketArticleService.getArticlesByBasketId(id);
    }
    @PostMapping("/{amount}/{id}")
    public BasketArticle createBasketArticle(@PathVariable int amount,@PathVariable Long id){
        User user  = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return basketArticleService.createBasketArticle(amount ,id,user);
    }
}
