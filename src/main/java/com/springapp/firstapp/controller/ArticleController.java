package com.springapp.firstapp.controller;

import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.Promotion;
import com.springapp.firstapp.module.User;
import com.springapp.firstapp.service.ArticleService;
import com.springapp.firstapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/article")
@AllArgsConstructor
@Transactional
public class ArticleController {
private final ArticleService articleService;
private final UserService userService;

    @GetMapping("")//valid
    public List<Article> getAllArticles (){return articleService.getAllArticles();}

    @PostMapping("")//valid
    public Article createArticle(@RequestBody Article article){return articleService.createArticle(article);}

    @DeleteMapping("/{id}")//valid
    public void deleteArticle(@PathVariable Long id){
        articleService.deleteArticleById(id);
    }

    @PutMapping("")//valid
    public Article updateArticle(@RequestBody Article article){
        return articleService.updateArticle(article);
    }

    @GetMapping("/{id}")//valid
    public Optional<Article> getArticleById(@PathVariable Long id){
        return articleService.getArticleById(id);
    }



    @GetMapping("bySubcategory/{id}")//valid
    public List<Article> getArticlesBySubcategoryId(@PathVariable Long id){
        return articleService.getArticlesBySubcategoryId(id);
    }
    @GetMapping("color/{color}")//valid
    public List<Article> getArticlesByColor(@PathVariable String color){
        return articleService.getArticlesByColor(color);
    }

    @GetMapping("price/{price}")//valid
    public List<Article> getArticlesByPrice(@PathVariable int price){
        return articleService.getArticleByPrice(price);
    }
    @GetMapping("code/{code}")//valid
    public Article getArticleByCode(@PathVariable Long code){return articleService.getArticleByCode(code);}


    @GetMapping("subCategoryName/{name}")//valid
    public List<Article> getArticlesBySubCategoryName(@PathVariable String name){
        return articleService.getArticlesBySubCategoryName(name);
    }
    @GetMapping("recommendation/{id}")//valid
    public List<Article>getArticlesRecommendationById(@PathVariable Long id){
        return articleService.getRecommendationsById(id);
    }
    @PostMapping("updateRecommendation/{id}")
    public List<Article>setArticlesRecommendation(@PathVariable Long id,@RequestBody List<Article> articles){
        return articleService.addArticleRecommendation(articles,id);
    }

    @PostMapping("deleteRecommendation/{id}")
    public List<Article>deleteArticlesRecommendation(@PathVariable Long id,@RequestBody List<Article> articles){
        return articleService.deleteArticleRecommendation(articles,id);
    }
    @GetMapping("insufficient/{stock}")//valid
    public List<Article> getInsufficientStockArticles(@PathVariable int stock){
        return articleService.getInsufficientStockArticles(stock);
    }
   @GetMapping("user/{id}")//valid
   public List<Article>getArticlesByUserId(@PathVariable Long id){
        return articleService.getArticlesByUserId(id);
   }
    @GetMapping("insufficientByUser/{id}/{stock}")//valid
    public  List<Article>getInsufficientArticlesByUser(@PathVariable Long id, @PathVariable int stock){
        User user=userService.getUserById(id).get();
        return  articleService.getInsufficientStockArticlesByProvider(user,stock);
    }

    @GetMapping("userInsufficientStock/{stock}")//valid
    public List<User> getUsersInsufficientStock(@PathVariable int stock){
        return articleService.getUsersStockInsufficient(stock);
    }

}