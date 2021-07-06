package com.springapp.firstapp.controller;

import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/article")
@AllArgsConstructor
public class ArticleController {
private final ArticleService articleService;

    @GetMapping("")
    public List<Article> getAllArticles (){return articleService.getAllArticles();}

    @PostMapping("")
    public Article createArticle(@RequestBody Article article){return articleService.createArticle(article);}

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id){
        articleService.deleteArticleById(ç);
    }

    @PutMapping("")
    public Article updateArticle(Article article){
        return articleService.updateArticle(article);
    }

    @GetMapping("/{id}")
    public Optional<Article> getArticleById(@PathVariable Long id){
        return articleService.getArticleById(id);
    }
    @GetMapping("bycategory/{id}")
    public List<Article> getArticlesByCategory(@PathVariable Long id){
        return articleService.getArticlesByCategoryId(id);
    }

    @GetMapping("bySubcategory/{id}")
    public List<Article> getArticlesBySubcategory(@PathVariable Long id){
        return articleService.getArticlesBySubcategoryId(id);
    }
    @GetMapping("color/{color}")
    public List<Article> getArticlesByColor(@PathVariable String color){
        return articleService.getArticlesByColor(color);
    }

    @GetMapping("price/{price}")
    public List<Article> getArticlesByPrice(@PathVariable int price){
        return articleService.getArticleByPrice(price);
    }

    @GetMapping("categoryName/{name}")
    public List<Article> getArticlesByCategory(@PathVariable String name){
        return articleService.getArticlesByCategoryName(name);
    }

    @GetMapping("subCategoryName/{name}")
    public List<Article> getArticlesBySubCategory(@PathVariable String name){
        return articleService.getArticlesBySubCategoryName(name);
    }

}
