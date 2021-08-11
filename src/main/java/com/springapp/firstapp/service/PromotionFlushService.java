package com.springapp.firstapp.service;
import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.Promotion;
import com.springapp.firstapp.module.PromotionFlush;
import com.springapp.firstapp.module.User;
import com.springapp.firstapp.repo.PromotionFlushRepo;
import lombok.AllArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class PromotionFlushService {
    private  final  PromotionFlushRepo promotionFlushRepo;
    private final ArticleService articleService;


    public  List<Article>getPromotionArticles(int percentage){
        PromotionFlush promotionFlush=promotionFlushRepo.getPromotionFlushByPercentagePromotion(percentage);
        return promotionFlush.getArticles();
    }
    public void deletePromotionArticlesByPercentage(Integer promotion){
        promotionFlushRepo.deletePromotionFlushByPercentagePromotion(promotion);
    }
    public void deletePromotionArticle(Long id,Long articleId){
        PromotionFlush promotion=promotionFlushRepo.findById(id).get();
        Article article=articleService.getArticleById(articleId).get();
        List<Article> articles=promotion.getArticles();
        articles.remove(article);
        promotionFlushRepo.save(promotion);
    }


    public PromotionFlush createPromotionFlush(Integer promotionPercentage, Date promotionExpiration, List<Long> articleIds){
        List<Article> articles = articleService.getArticlesByIds(articleIds);

        PromotionFlush promotionFlush=new PromotionFlush(null,promotionPercentage,promotionExpiration,articles);
        return promotionFlushRepo.save(promotionFlush);
    }
    public List<PromotionFlush> getPromotionFlush(){
        Date date=new Date();
        return promotionFlushRepo.getPromotionFlushesByPromotionExpirationAfter(date);
    }
    public void deletePromotionFlush(){
        Date date=new Date();
        promotionFlushRepo.deletePromotionFlushesByPromotionExpirationBefore(date);
    }
    public PromotionFlush getPromotionById(Long id){
        return promotionFlushRepo.findById(id).get();
    }

    public void deletePromotion(Long id) {
        promotionFlushRepo.deleteById(id);
    }

    public PromotionFlush addPromotionArticles(List<Long> articleIds, Long promotionId) {
        List<Article> articles = articleService.getArticlesByIds(articleIds);
        PromotionFlush promotionFlush = getPromotionById(promotionId);
        List<Article> promotionArticles=promotionFlush.getArticles();
        for (Article article :articles){
            promotionArticles.add(article);
        }
        return promotionFlushRepo.save(promotionFlush);
    }

    public Set<PromotionFlush> getPromotionProvider(Long id) {
        List<Article> articles = articleService.getArticlesByUserId(id);
        return promotionFlushRepo.getPromotionFlushesByArticlesIn(articles);

    }

    public List<Article> getPromotionArticlesProvider(Long promotionId) {
        PromotionFlush promotionFlush=getPromotionById(promotionId);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<PromotionFlush> promotions = getPromotionProvider(user.getId());
        if (promotions.contains(promotionFlush)) {
            List<Article> articles = new ArrayList<>();
            List<Article> promotionArticles = promotionFlush.getArticles();
            List<Article> providerArticles=articleService.getArticlesByUserId(user.getId());
            for (Article article : promotionArticles) {
                if (providerArticles.contains(article)) {
                    articles.add(article);

                }
            }
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
        List<PromotionFlush>promotions=getPromotionFlush();
        for (PromotionFlush promotionFlush:promotions){
            for (Article article:promotionFlush.getArticles()){
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
