package com.springapp.firstapp.controller;


import com.springapp.firstapp.dto.BasketArticleRequest;
import com.springapp.firstapp.module.BasketArticle;
import com.springapp.firstapp.service.BasketArticleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/basketArticle")
@AllArgsConstructor
public class BasketArticleController {
    private  final BasketArticleService basketArticleService;
    @GetMapping("/{id}")
    public List<BasketArticle> getArticlesByBasketId(@PathVariable Long id){
        return basketArticleService.getArticlesByBasketId(id);
    }
    @PutMapping("")
    public BasketArticle updateBasketArticle(@RequestBody BasketArticleRequest basketArticleRequest){
        return basketArticleService.update(basketArticleRequest);
    }
    @DeleteMapping("/{id}")
    public void deleteBasketArticle(@PathVariable Long id){
        basketArticleService.deleteBasketArticle(id);
    }
}
