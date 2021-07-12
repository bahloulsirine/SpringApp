package com.springapp.firstapp.controller;

import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.service.BasketArticleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/basketArticle")
@AllArgsConstructor
public class BasketArticleController {
    private  final BasketArticleService basketArticleService;
    @GetMapping("/{id}")
    public List<Article> getArticlesByBasketId(@PathVariable Long id){
        return basketArticleService.getArticlesByBasketId(id);
    }
}
