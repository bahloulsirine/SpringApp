package com.springapp.firstapp.controller;

import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.Promotion;
import com.springapp.firstapp.module.User;
import com.springapp.firstapp.repo.UserRepo;
import com.springapp.firstapp.service.ArticleService;
import com.springapp.firstapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
//private final UserService userService;
private final UserRepo userRepo;


    @GetMapping("")//valid
    public List<Article> getAllArticles (){return articleService.getAllArticles();}

    @PreAuthorize("has_roles(ADMIN,PROVIDER)")
    @PostMapping("")//valid
    public Article createArticle(@RequestBody Article article){return articleService.createArticle(article);}

    @PreAuthorize("has_role(ADMIN)")
    @DeleteMapping("/{id}")//valid
    public void deleteArticle(@PathVariable Long id){
        articleService.deleteArticleById(id);
    }

    @PreAuthorize("has_roles(ADMIN,PROVIDER)")
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
    @PreAuthorize("has_roles(ADMIN,PROVIDER)")
    @PutMapping("addRecommendation/{id}")//valid
    public List<Article>addArticlesRecommendation(@PathVariable Long id,@RequestBody List<Article> articles){
        return articleService.addArticleRecommendation(articles,id);
    }
    @PreAuthorize("has_roles(ADMIN,PROVIDER)")
    @DeleteMapping("deleteRecommendation/{id}")
    public void deleteArticlesRecommendation(@PathVariable Long id,@RequestBody List<Article> articles){
       articleService.deleteArticleRecommendation(articles,id);
    }
    @PreAuthorize("has_roles(ADMIN,PROVIDER)")
    @GetMapping("insufficient/{stock}")//valid
    public List<Article> getInsufficientStockArticles(@PathVariable int stock){
        return articleService.getInsufficientStockArticles(stock);
    }
    @PreAuthorize("has_roles(ADMIN,PROVIDER)")
   @GetMapping("/ArticleByUser")//valid
   public List<Article>getArticlesByUserId(){
       String mail  = SecurityContextHolder.getContext().getAuthentication().getName();
       User user=userRepo.getUserByEmail(mail);
        return articleService.getArticlesByUserId(user.getId());
   }
    @PreAuthorize("has_roles(ADMIN,PROVIDER)")
    @GetMapping("insufficientByUser/{stock}")//valid
    public  List<Article>getInsufficientArticlesByUser(@PathVariable int stock){
        String mail  = SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepo.getUserByEmail(mail);
        return  articleService.getInsufficientStockArticlesByProvider(user,stock);
    }
    @PreAuthorize("has_roles(ADMIN)")
    @GetMapping("userInsufficientStock/{stock}")//valid
    public List<User> getUsersInsufficientStock(@PathVariable int stock){
        return articleService.getUsersStockInsufficient(stock);
    }

}