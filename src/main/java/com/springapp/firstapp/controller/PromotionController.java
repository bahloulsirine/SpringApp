package com.springapp.firstapp.controller;
import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.Promotion;
import com.springapp.firstapp.service.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("api/promotion")
@AllArgsConstructor
@Transactional
@CrossOrigin (origins = "*",allowedHeaders = "*")

public class PromotionController {
    private final PromotionService promotionService;

    @GetMapping("")//valid
    public List<Promotion> getAllPromotionArticles(){
        return promotionService.getAllPromotionArticles();
    }
    @GetMapping("promotionPercentage/{percentage}")//valid
    public List<Article> getPromotionArticles(@PathVariable int percentage){
        return promotionService.getPromotionArticles(percentage);
    }

    //@PreAuthorize("hasAnyRole(ADMIN,PROVIDER)")
    @PostMapping("/{promotionPercentage}")//valid
    public Promotion createPromotionArticles(@PathVariable int promotionPercentage,@RequestBody List<Long> articleIds){

        return promotionService.createPromotionArticles(articleIds,promotionPercentage);
    }
    //@PreAuthorize("hasAnyRole(ADMIN,PROVIDER)")
    @PutMapping("/addPromotion/{promotionPercentage}")//valid
    public Promotion addPromotionArticles(@PathVariable int promotionPercentage,@RequestBody List<Long> articleIds){

        return promotionService.addPromotionArticles(articleIds,promotionPercentage);
    }
    //@PreAuthorize("hasAnyRole(ADMIN,PROVIDER)")
    @DeleteMapping ("/deletePromotionArticle/{id}/{percentage}")
    public void deletePromotionArticles(@PathVariable Long id,@PathVariable int percentage){
        promotionService.deletePromotionArticle(id,percentage);
    }
    //@PreAuthorize("hasAnyRole(ADMIN,PROVIDER)")
    @DeleteMapping("deletePromotionByPercentage/{promotionPercentage}") //valid
    public void deletePromotionByPercentage(@PathVariable int promotionPercentage){
       promotionService.deletePromotionArticlesByPercentage(promotionPercentage);
    }
    //@PreAuthorize("hasAnyRole(ADMIN,PROVIDER)")
    @DeleteMapping("deletePromotionById/{id}") //valid
    public void deletePromotion(@PathVariable Long id){
        promotionService.deletePromotion(id);
    }



}
