package com.springapp.firstapp.controller;

import com.springapp.firstapp.dto.ArticleCreateRequest;
import com.springapp.firstapp.dto.ArticleUpdateRequest;
import com.springapp.firstapp.dto.DeleteRecommendationRequest;
import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.SubCategory;
import com.springapp.firstapp.module.User;
import com.springapp.firstapp.repo.UserRepo;
import com.springapp.firstapp.service.ArticleService;
import com.springapp.firstapp.service.SubCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/article")
@AllArgsConstructor
@Transactional
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ArticleController {
    private final ArticleService articleService;
    private final UserRepo userRepo;

    @GetMapping("")//valid
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @PreAuthorize("hasAnyRole('ADMIN','PROVIDER')")
    @PostMapping("")//valid
    public Article createArticle(@RequestBody ArticleCreateRequest req) {
        return articleService.createArticle(req);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")//valid
    public void deleteArticle(@PathVariable Long id) {
        articleService.deleteArticleById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','PROVIDER')")
    @PutMapping("")//valid
    public Article updateArticleProvider(@RequestBody ArticleUpdateRequest articleUpdateRequest) {

            Article article = articleService.getArticleToUpdate(articleUpdateRequest.getId());
            return articleService.updateArticleProvider(articleUpdateRequest,article.getId());
    }

    @GetMapping("/{id}")//valid
    public ResponseEntity<Optional<Article>> getArticleById(@PathVariable Long id) {
        if (articleService.getArticleById(id).isPresent()) {
            return new ResponseEntity<>(articleService.getArticleById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(articleService.getArticleById(id), HttpStatus.NOT_FOUND);
    }
    @GetMapping("getToUpdate/{id}")//valid
    public Article getArticle(@PathVariable Long id) {
        return articleService.getArticleToUpdate(id);
    }

    @GetMapping("bySubcategory/{id}")//valid
    public List<Article> getArticlesBySubcategoryId(@PathVariable Long id) {
        return articleService.getArticlesBySubcategoryId(id);
    }

    @GetMapping("color/{color}")//valid
    public List<Article> getArticlesByColor(@PathVariable String color) {
        return articleService.getArticlesByColor(color);
    }

    @GetMapping("price/{price}")//valid
    public List<Article> getArticlesByPrice(@PathVariable int price) {
        return articleService.getArticleByPrice(price);
    }

    @GetMapping("code/{code}")//valid
    public Article getArticleByCode(@PathVariable Long code) {
        return articleService.getArticleByCode(code);
    }

    @GetMapping("subCategoryName/{name}")//valid
    public List<Article> getArticlesBySubCategoryName(@PathVariable String name) {
        return articleService.getArticlesBySubCategoryName(name);
    }

    @GetMapping("categoryId/{id}")//valid
    public List<Article> getArticlesByCategoryId(@PathVariable Long id) {
        return articleService.getArticlesByCategoryId(id);
    }

    @GetMapping("recommendation/{id}")//valid
    public List<Article> getArticlesRecommendationById(@PathVariable Long id) {
        return articleService.getRecommendationsById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("addRecommendation/{id}")//valid
    public List<Article> addArticlesRecommendation(@PathVariable Long id, @RequestBody List<Long> articlesIds) {
        return articleService.addArticleRecommendation(articlesIds, id);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("deleteRecommendation")
    public void deleteArticlesRecommendation(@RequestBody DeleteRecommendationRequest deleteRecommendationRequest) {
        articleService.deleteArticleRecommendation(deleteRecommendationRequest.getArticleId(), deleteRecommendationRequest.getRecommendationId());
    }

    @PreAuthorize("hasAnyRole('ADMIN','PROVIDER')")
    @GetMapping("insufficient/{stock}")//valid
    public List<Article> getInsufficientStockArticles(@PathVariable int stock) {
        return articleService.getInsufficientStockArticles(stock);
    }

    @PreAuthorize("hasAnyRole('ADMIN','PROVIDER')")
    @GetMapping("/articleByUser/{id}")//valid
    public List<Article> getArticlesByUserId(@PathVariable Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.hasRole("ADMIN")) {
            return articleService.getArticlesByUserId(id);
        } else {
            return articleService.getArticlesByUserId(user.getId());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN','PROVIDER')")
    @GetMapping("insufficientByUser/{stock}/{id}")//valid
    public List<Article> getInsufficientArticlesByUser(@PathVariable int stock, @PathVariable Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.hasRole("ADMIN")) {
            return articleService.getInsufficientStockArticlesByProvider(userRepo.getUserById(id), stock);
        } else {
            return articleService.getInsufficientStockArticlesByProvider(user, stock);
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("userInsufficientStock/{stock}")//valid
    public List<User> getUsersInsufficientStock(@PathVariable int stock) {
        return articleService.getUsersStockInsufficient(stock);
    }

    @GetMapping("name/{name}")
    public List<Article> getArticlesByName(@PathVariable String name) {
        return articleService.getArticlesByName(name);
    }

    @GetMapping("image/{url}")
    public Article getArticleByImage(@PathVariable String url) {
        return articleService.getArticleByImage(url);
    }

}