package com.springapp.firstapp.controller;

import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.Promotion;
import com.springapp.firstapp.module.PromotionFlush;
import com.springapp.firstapp.module.User;
import com.springapp.firstapp.service.PromotionFlushService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("api/promotionFlush")
@AllArgsConstructor
@Transactional
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class PromotionFlushController {
    private final PromotionFlushService promotionFlushService;
    @PreAuthorize("hasAnyRole('ADMIN','PROVIDER')")
    @PutMapping("/addPromotion/{promotionId}")//valid
    public PromotionFlush addPromotionArticles(@PathVariable Long promotionId, @RequestBody List<Long> articleIds){

        return promotionFlushService.addPromotionArticles(articleIds,promotionId);
    }

    @PreAuthorize("hasAnyRole('ADMIN','PROVIDER')")
    @DeleteMapping ("/deletePromotionArticle/{promotionId}/{id}")
    public void deletePromotionArticles(@PathVariable Long promotionId,@PathVariable Long id){
        promotionFlushService.deletePromotionArticle(promotionId,id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping ("/deletePromotionById/{id}")
    public void deletePromotionById(@PathVariable Long id){
        promotionFlushService.deletePromotion(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN','PROVIDER')")
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
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("")//valid
    public void deletePromotionFlush(){
        promotionFlushService.deletePromotionFlush();
    }


    @PreAuthorize("hasRole('PROVIDER')")
    @GetMapping("promotionProvider")
    public Set<PromotionFlush> getPromotionProviders(){
        User user  = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return promotionFlushService.getPromotionProvider(user.getId());
    }
    @PreAuthorize("hasRole('PROVIDER')")
    @GetMapping("promotionProviderArticles/{promotionId}")
    public List<Article> getPromotionArticlesProviders(@PathVariable Long promotionId){
        return promotionFlushService.getPromotionArticlesProvider(promotionId);
    }

    @PreAuthorize("hasAnyRole('PROVIDER','ADMIN')")
    @GetMapping("noPromotionArticles")
    public List<Article> getNoPromotionArticles(){
        return promotionFlushService.getArticlesToPromotion();
    }
}
