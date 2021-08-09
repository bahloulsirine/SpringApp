package com.springapp.firstapp.controller;

import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.PromotionFlush;
import com.springapp.firstapp.service.PromotionFlushService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;


@RestController
@RequestMapping("api/promotionFlush")
@AllArgsConstructor
@Transactional
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class PromotionFlushController {
    private final PromotionFlushService promotionFlushService;
    //@PreAuthorize("hasAnyRole(ADMIN,PROVIDER)")
    @PutMapping("/addPromotion/{promotionId}")//valid
    public PromotionFlush addPromotionArticles(@PathVariable Long promotionId, @RequestBody List<Long> articleIds){

        return promotionFlushService.addPromotionArticles(articleIds,promotionId);
    }

    //@PreAuthorize("hasAnyRole(ADMIN,PROVIDER)")
    @DeleteMapping ("/deletePromotionArticle/{promotionId}/{id}")
    public void deletePromotionArticles(@PathVariable Long promotionId,@PathVariable Long id){
        promotionFlushService.deletePromotionArticle(promotionId,id);
    }
    //@PreAuthorize("hasAnyRole(ADMIN,PROVIDER)")
    @DeleteMapping ("/deletePromotionById/{id}")
    public void deletePromotionById(@PathVariable Long id){
        promotionFlushService.deletePromotion(id);
    }
    //@PreAuthorize("hasAnyRole(ADMIN,PROVIDER)")
    @PostMapping("/{promotionPercentage}/{promotionExpiration}")//invalid
    public PromotionFlush createPromotionFlush(@PathVariable int promotionPercentage, @PathVariable Date promotionExpiration, @RequestBody List<Long> articleIds){
        return promotionFlushService.createPromotionFlush(promotionPercentage,promotionExpiration,articleIds);
    }

    @GetMapping("")//valid
    public List<PromotionFlush> getPromotionFlush(){
        return promotionFlushService.getPromotionFlush();
    }
    @GetMapping("/{id}")
    public PromotionFlush getById(@PathVariable Long id){
        return promotionFlushService.getPromotionById(id);
    }

    @GetMapping("promotionPercentage/{percentage}")//valid
    public List<Article> getPromotionArticles(@PathVariable int percentage){
        return promotionFlushService.getPromotionArticles(percentage);
    }
    //@PreAuthorize("hasAnyRole(ADMIN,PROVIDER)")
    @DeleteMapping("")//valid
    public void deletePromotionFlush(){
        promotionFlushService.deletePromotionFlush();
    }
}
