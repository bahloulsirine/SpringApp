package com.springapp.firstapp.service;

import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.Promotion;
import com.springapp.firstapp.module.User;
import com.springapp.firstapp.repo.ArticleRepo;
import com.springapp.firstapp.repo.PromotionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ArticleService {
    private final  ArticleRepo articleRepo;
    private final  UserService userService;
    private final PromotionRepo promotionRepo;

    public List<Article> getAllArticles (){
        return articleRepo.findAll();
    }

    public Article createArticle(Article article){
        return articleRepo.save(article);
    }

    public void deleteArticleById(Long id){
        articleRepo.deleteById(id);
    }

    public Article updateArticle(Article article){
        return articleRepo.save(article);
    }

    public Optional<Article> getArticleById(Long id){
        return articleRepo.findById(id);
    }


    public List <Article> getArticlesBySubcategoryId(Long id){
        return articleRepo.getArticlesBySubCategoryId(id);
    }
    public List <Article> getArticlesByColor(String color){
        return articleRepo.getArticlesByColor(color);
    }
    public Article getArticleByCode(Long code){ return articleRepo.getArticleByCode(code);}
    public List<Article>getArticleByPrice(int price){return articleRepo.getArticlesByPrice(price);}
    public List<Article>getArticlesBySubCategoryName(String name){return articleRepo.getArticlesBySubCategoryName(name);}
    public List<Article>getRecommendationsById(Long id){
        Optional<Article> article1=getArticleById(id);
        return article1.get().getArticles();}
    public List<Article> addArticleRecommendation(List<Article> articles,Long id){
        Article article1=getArticleById(id).get();
        List<Article> list=getRecommendationsById(id);
        for (Article article:articles){
        list.add(article);}
        article1.setArticles(list);
        articleRepo.save(article1);
        return list;}
    public void deleteArticleRecommendation(List<Article> articles, Long id){
        Article article1=getArticleById(id).get();
        List<Long>articlesIds=new ArrayList<>();
        for (Article article:articles){
           articlesIds.add(article.getId());
            }
        articleRepo.deleteRecommendations(articlesIds);

    }
    public List<Article> getInsufficientStockArticles(int stock){ return articleRepo.getArticlesByStockLessThan(stock);}
    public List<Article>getInsufficientStockArticlesByProvider(User user,int stock){
        return articleRepo.getArticlesByStockLessThanAndUser(stock,user);
    }
    public List<Article>getArticlesByUserId(Long id){return articleRepo.getArticlesByUserId(id);}
    public List<User> getUsersStockInsufficient(int stock){
        List<Long> userIds =  articleRepo.getUserIdInsufficientStock(stock);
        return userService.getUsersByIdIn(userIds);
    }




}
