package com.springapp.firstapp.service;
import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.Promotion;
import com.springapp.firstapp.module.User;
import com.springapp.firstapp.repo.PromotionRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PromotionService {
    private final PromotionRepo promotionRepo;
    private final ArticleService articleService;

    public List<Promotion> getAllPromotionArticles() {
        List<Promotion> promotions = promotionRepo.findAll();

        return promotions;
    }

    public List<Article> getPromotionArticles(int percentage) {
        Promotion promotion = promotionRepo.getPromotionByPercentagePromotion(percentage);
        return promotion.getArticles();
    }

    public Promotion createPromotionArticles(List<Long> articleIds, Integer percentage) {
        List<Article> articles = articleService.getArticlesByIds(articleIds);
        Promotion promotion=promotionRepo.getPromotionByPercentagePromotion(percentage);
        if(promotion==null){
            Promotion newPromotion = new Promotion(null, percentage, articles);
            return promotionRepo.save(newPromotion);
        }else{
            List<Article>promotionArticles=promotion.getArticles();
            for(Article article:articles){
               promotionArticles.add(article);
            }
            promotion.setArticles(promotionArticles);
            return promotionRepo.save(promotion);
        }
    }

    public void deletePromotionArticlesByPercentage(Integer promotion) {
        promotionRepo.deletePromotionByPercentagePromotion(promotion);
    }

    public void deletePromotionArticle(Long id, int percentage) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Promotion promotion = promotionRepo.getPromotionByPercentagePromotion(percentage);

        if (user.hasRole("ADMIN")) {

            Article article = articleService.getArticleById(id).get();
            List<Article> articles = promotion.getArticles();
            articles.remove(article);
            promotionRepo.save(promotion);
        } else {
            if (getPromotionProvider(user.getId()).contains(promotion)) {
                Article article = articleService.getArticleById(id).get();
                List<Article> articles = promotion.getArticles();
                articles.remove(article);
                promotionRepo.save(promotion);
            }
        }
    }


    public void deletePromotion(Long id) {
        promotionRepo.deleteById(id);
    }

    public Promotion addPromotionArticles(List<Long> articleIds, int promotionPercentage) {
        List<Article> articles = articleService.getArticlesByIds(articleIds);
        Promotion promotion = promotionRepo.getPromotionByPercentagePromotion(promotionPercentage);
        List<Article> promotionArticles = promotion.getArticles();
        for (Article article : articles) {
            promotionArticles.add(article);
        }
        return promotionRepo.save(promotion);
    }

    public Set<Promotion> getPromotionProvider(Long id) {
        List<Article> articles = articleService.getArticlesByUserId(id);
        return promotionRepo.getPromotionsByArticlesIn(articles);

    }

    public List<Article> getPromotionArticlesProvider(Integer percentage) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       Set<Promotion> promotions = getPromotionProvider(user.getId());
        if (promotions.contains(promotionRepo.getPromotionByPercentagePromotion(percentage))) {
            List<Article> articles = new ArrayList<>();
            List<Article> promotionArticles = getPromotionArticles(percentage);
            List<Article> providerArticles=articleService.getArticlesByUserId(user.getId());
            articles = promotionArticles.stream().filter(providerArticles::contains).collect(Collectors.toList());
//            for (Article article : promotionArticles) {
//                if (providerArticles.contains(article)) {
//                    articles.add(article);
//                }
//            }
            return articles;
        } else {
            return null;
        }

    }
    public List<Article> getArticlesToPromotion(){
        List<Article>articles=new ArrayList<>();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(user.hasRole("ADMIN")){
            articles=articleService.getAllArticles();
        }else{
            articles=articleService.getArticlesByUserId(user.getId());
        }
        List<Article>promotionArticles=new ArrayList<>();
        List<Promotion>promotions=getAllPromotionArticles();
        for (Promotion promotion:promotions){
           for (Article article:promotion.getArticles()){
               promotionArticles.add(article);
           }
        }
        List<Article>articleNoPromotion=new ArrayList<>();
        for (Article article:articles){
            if (!promotionArticles.contains(article)){
                articleNoPromotion.add(article);
            }
        }
        return articleNoPromotion;

    }
}