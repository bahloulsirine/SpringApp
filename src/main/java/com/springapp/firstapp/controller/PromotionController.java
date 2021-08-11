package com.springapp.firstapp.controller;
import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.Promotion;
import com.springapp.firstapp.module.User;
import com.springapp.firstapp.service.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

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
    @PreAuthorize("hasAnyRole('ADMIN','PROVIDER')")
    @PostMapping("/{promotionPercentage}")//valid
    public Promotion createPromotionArticles(@PathVariable int promotionPercentage,@RequestBody List<Long> articleIds){

        return promotionService.createPromotionArticles(articleIds,promotionPercentage);
    }

    @PreAuthorize("hasAnyRole('ADMIN','PROVIDER')")
    @PutMapping("/addPromotion/{promotionPercentage}")//valid
    public Promotion addPromotionArticles(@PathVariable int promotionPercentage,@RequestBody List<Long> articleIds){

        return promotionService.addPromotionArticles(articleIds,promotionPercentage);
    }
    @PreAuthorize("hasAnyRole('ADMIN','PROVIDER')")
    @DeleteMapping ("/deletePromotionArticle/{id}/{percentage}")
    public void deletePromotionArticles(@PathVariable Long id,@PathVariable int percentage){
        promotionService.deletePromotionArticle(id,percentage);
    }
@PreAuthorize("hasRole('ADMIN')")

    @DeleteMapping("deletePromotionByPercentage/{promotionPercentage}") //valid
    public void deletePromotionByPercentage(@PathVariable int promotionPercentage){
       promotionService.deletePromotionArticlesByPercentage(promotionPercentage);
    }

@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("deletePromotionById/{id}") //valid
    public void deletePromotion(@PathVariable Long id){
        promotionService.deletePromotion(id);
    }

    @PreAuthorize("hasRole('PROVIDER')")
    @GetMapping("promotionProvider")
    public Set<Promotion> getPromotionProviders(){
    User user  = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
   return promotionService.getPromotionProvider(user.getId());
}
    @PreAuthorize("hasRole('PROVIDER')")
    @GetMapping("promotionProviderArticles/{percentage}")
    public List<Article> getPromotionArticlesProviders(@PathVariable Integer percentage){
        return promotionService.getPromotionArticlesProvider(percentage);
    }

    @PreAuthorize("hasAnyRole('PROVIDER','ADMIN')")
    @GetMapping("noPromotionArticles")
    public List<Article> getNoPromotionArticles(){
        return promotionService.getArticlesToPromotion();
    }

}
