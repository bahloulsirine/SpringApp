package com.springapp.firstapp.service;

import com.springapp.firstapp.dto.PromotionRequest;
import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.Promotion;
import com.springapp.firstapp.repo.PromotionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<Promotion> promotions=new ArrayList<>();
        for (Article article:articles){
            Promotion promotion=new Promotion(null,percentage,null,article);
            promotionRepo.save(promotion);
            promotions.add(promotion);
        }
        return promotions;}

    public void deletePromotionArticles(Integer promotion){
        promotionRepo.deletePromotionsByPercentagePromotion(promotion);
    }

    public  List<Promotion> createPromotionFlush(PromotionRequest promotionRequest){
        List<Promotion> promotions=new ArrayList<>();

        for (Article article:promotionRequest.getArticles()){
            Promotion promotion=new Promotion(null,promotionRequest.getPercentage(),promotionRequest.getExpirationDate(),article);
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
