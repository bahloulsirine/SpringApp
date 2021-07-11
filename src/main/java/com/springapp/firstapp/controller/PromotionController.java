package com.springapp.firstapp.controller;

import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.Promotion;
import com.springapp.firstapp.service.PromotionService;
import lombok.AllArgsConstructor;
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
    @PostMapping("/{promotionPercentage}")//invalid
    public List<Promotion>createPromotionArticles(@PathVariable int promotionPercentage,@RequestBody List<Article>articles){
        return promotionService.createPromotionArticles(articles,promotionPercentage);
    }

    @DeleteMapping("deletePromotion/{promotionPercentage}") //valid
    public void deletePromotionArticles(@PathVariable int promotionPercentage){
       promotionService.deletePromotionArticles(promotionPercentage);
    }
    @PostMapping("createPromotionFlush/{percentage}/{date}")//invalid
    public List<Promotion> createPromotionFlush(@PathVariable Date date, @PathVariable  int percentage, @RequestBody List<Article> articles){
        return promotionService.createPromotionFlush(date,percentage,articles);
    }
    @GetMapping("/PromotionFlush")//valid
    public List<Promotion> getPromotionFlush(){
        return promotionService.getPromotionFlush();
    }
    @DeleteMapping("/deletePromotionFlush")//valid
    public void deletePromotionFlush(){
        promotionService.deletePromotionFlush();
    }
}
