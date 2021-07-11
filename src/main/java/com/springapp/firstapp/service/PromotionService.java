package com.springapp.firstapp.service;

import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.Promotion;
import com.springapp.firstapp.repo.PromotionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class PromotionService {
    private final PromotionRepo promotionRepo;
    public List<Promotion> getAllPromotionArticles(){
        List<Promotion> promotions=promotionRepo.findAll();

        return promotions;
    }
    public  List<Promotion>getPromotionArticles(int percentage){
        List<Promotion> promotions=promotionRepo.getPromotionsByPercentagePromotion(percentage);
        return promotions;
    }
    public List<Promotion>createPromotionArticles(List<Article> articles, Integer percentage){
        List<Promotion> promotions=null;
        for (Article article:articles){
            Promotion promotion=new Promotion(null,percentage,null,article);
            promotionRepo.save(promotion);
            promotions.add(promotion);
        }
        return promotions;}

    public void deletePromotionArticles(Integer promotion){
        promotionRepo.deletePromotionsByPercentagePromotion(promotion);
    }

    public  List<Promotion> createPromotionFlush(Date date, int percentage, List<Article> articles){
        List<Promotion> promotions=null;
        for (Article article:articles){
            Promotion promotion=new Promotion(null,percentage,date,article);
            promotionRepo.save(promotion);
            promotions.add(promotion);
        }
        return promotions;
    }
    public List<Promotion> getPromotionFlush(){
        Date date=new Date();
        return promotionRepo.getPromotionsByPromotionExpirationAfter(date);
    }
    public void deletePromotionFlush(){
        Date date=new Date();
        promotionRepo.deletePromotionsByPromotionExpirationBefore(date);
    }
}
