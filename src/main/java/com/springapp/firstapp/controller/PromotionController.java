package com.springapp.firstapp.controller;

import com.springapp.firstapp.dto.PromotionRequest;
import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.Promotion;
import com.springapp.firstapp.module.User;
import com.springapp.firstapp.service.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/promotion")
@AllArgsConstructor
@Transactional
public class PromotionController {
    private final PromotionService promotionService;

    @GetMapping("")//valid
    public List<Promotion> getAllPromotionArticles(){
        return promotionService.getAllPromotionArticles();
    }
    @GetMapping("promotionPercentage/{percentage}")//valid
    public List<Promotion> getPromotionArticles(@PathVariable int percentage){
        return promotionService.getPromotionArticles(percentage);
    }

    @PreAuthorize("has_roles(ADMIN,PROVIDER)")
    @PostMapping("/{promotionPercentage}")//valid
    public List<Promotion>createPromotionArticles(@PathVariable int promotionPercentage,@RequestBody List<Article>articles){
        return promotionService.createPromotionArticles(articles,promotionPercentage);
    }
    @PreAuthorize("has_roles(ADMIN,PROVIDER)")
    @DeleteMapping("deletePromotion/{promotionPercentage}") //valid
    public void deletePromotionArticles(@PathVariable int promotionPercentage){
       promotionService.deletePromotionArticles(promotionPercentage);
    }
    @PreAuthorize("has_roles(ADMIN,PROVIDER)")
    @PostMapping("/createPromotionFlush")//invalid
    public List<Promotion> createPromotionFlush(@RequestBody PromotionRequest promotionRequest){
        return promotionService.createPromotionFlush(promotionRequest);
    }

    @GetMapping("/PromotionFlush")//valid
    public List<Promotion> getPromotionFlush(){
        return promotionService.getPromotionFlush();
    }

    @PreAuthorize("has_roles(ADMIN,PROVIDER)")
    @DeleteMapping("/deletePromotionFlush")//valid
    public void deletePromotionFlush(){
        promotionService.deletePromotionFlush();
    }
}
