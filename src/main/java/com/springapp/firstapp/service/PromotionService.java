package com.springapp.firstapp.service;
import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.Promotion;
import com.springapp.firstapp.repo.PromotionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class PromotionService {
    private final PromotionRepo promotionRepo;
    private final ArticleService articleService;
    public List<Promotion> getAllPromotionArticles(){
        List<Promotion> promotions=promotionRepo.findAll();

        return promotions;
    }
    public  List<Article>getPromotionArticles(int percentage){
        Promotion promotion=promotionRepo.getPromotionByPercentagePromotion(percentage);
        return promotion.getArticles();
    }
    public Promotion createPromotionArticles(List<Long> articleIds, Integer percentage){
        List<Article> articles = articleService.getArticlesByIds(articleIds);
        Promotion promotion = new Promotion(null,percentage,articles);
        return promotionRepo.save(promotion);}

    public void deletePromotionArticlesByPercentage(Integer promotion){
        promotionRepo.deletePromotionByPercentagePromotion(promotion);
    }
    public void deletePromotionArticle(Long id,int percentage){
        Promotion promotion=promotionRepo.getPromotionByPercentagePromotion(percentage);
        Article article=articleService.getArticleById(id).get();
        List<Article> articles=promotion.getArticles();
        articles.remove(article);
        promotionRepo.save(promotion);
    }



    public void deletePromotion(Long id) {
        promotionRepo.deleteById(id);
    }

    public Promotion addPromotionArticles(List<Long> articleIds, int promotionPercentage) {
        List<Article> articles = articleService.getArticlesByIds(articleIds);
        Promotion promotion = promotionRepo.getPromotionByPercentagePromotion(promotionPercentage);
        List<Article> promotionArticles=promotion.getArticles();
        for (Article article :articles){
            promotionArticles.add(article);
        }
        return promotionRepo.save(promotion);
    }
}
