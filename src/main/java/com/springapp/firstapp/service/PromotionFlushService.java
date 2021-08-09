package com.springapp.firstapp.service;
import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.PromotionFlush;
import com.springapp.firstapp.repo.PromotionFlushRepo;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

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
}
