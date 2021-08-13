package com.springapp.firstapp.service;

import com.springapp.firstapp.dto.ArticleCreateRequest;
import com.springapp.firstapp.dto.ArticleUpdateRequest;
import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.Promotion;
import com.springapp.firstapp.module.SubCategory;
import com.springapp.firstapp.module.User;
import com.springapp.firstapp.repo.ArticleRepo;
import com.springapp.firstapp.repo.PromotionRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final SubCategoryService subCategoryService;
    private final CategoryService categoryService;

    public List<Article> getAllArticles (){
        return articleRepo.findAll();
    }

    public Article createArticle(ArticleCreateRequest req){
        User user = userService.getUserById(req.getUserId()).get();
        SubCategory sub = subCategoryService.getSubCategoryById(req.getSubCategoryId()).get();
        Article article = new Article(null,req.getCode(), req.getName(), req.getStock(), req.getTVA(), req.getDescription(), req.getColor(), req.getWeight(), req.getPrice(),req.getUrl(), sub,null,user);
        return articleRepo.save(article);
    }

    public void deleteArticleById(Long id){
        articleRepo.deleteById(id);
    }

    public Article updateArticleProvider(ArticleUpdateRequest articleUpdateRequest,Long articleId){
        Article article=getArticleById(articleId).get();
            article.setPrice(articleUpdateRequest.getPrice());
            article.setStock(articleUpdateRequest.getStock());
            article.setColor(articleUpdateRequest.getColor());
            article.setDescription(articleUpdateRequest.getDescription());
            article.setTVA(articleUpdateRequest.getTVA());
            article.setWeight(articleUpdateRequest.getWeight());
            article.setName(articleUpdateRequest.getName());
            article.setUrl(articleUpdateRequest.getUrl());
            return articleRepo.save(article);

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
    public List<Article> addArticleRecommendation(List<Long> articlesIds,Long id){
        Article article=getArticleById(id).get();
        List<Article> list=getRecommendationsById(id);
        for (Long articleId:articlesIds){
        list.add(getArticleById(articleId).get());}
        article.setArticles(list);
        articleRepo.save(article);
        return list;}
    public void deleteArticleRecommendation(Long articleId, Long recommendationId){
        articleRepo.deleteRecommendations(articleId,recommendationId);

    }
    public List<Article> getInsufficientStockArticles(int stock){ return articleRepo.getArticlesByStockLessThan(stock);}
    public List<Article>getInsufficientStockArticlesByProvider(User user,int stock){
        return articleRepo.getArticlesByStockLessThanAndUser(stock,user);
    }
    public List<Article>getArticlesByUserId(Long id){

        return articleRepo.getArticlesByUserId(id);}
    public List<User> getUsersStockInsufficient(int stock){
        List<Long> userIds =  articleRepo.getUserIdInsufficientStock(stock);
        return userService.getUsersByIdIn(userIds);
    }


    public List<Article> getArticlesByIds(List<Long> articleIds) {
        return articleRepo.getArticlesByIdIn(articleIds);
    }
public Article getArticleByImage(String url){
        return articleRepo.getArticleByUrl(url);
}
public List<Article> getArticlesByName(String name){
        return articleRepo.getArticlesByName(name);
}
public List<Article> getArticlesByCategoryId(Long id){

        return  articleRepo.getArticlesBySubCategoryCategory(categoryService.getCategoryById(id).get());
}

    public Article getArticleToUpdate(Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user.hasRole("PROVIDER")){
        return articleRepo.findByIdAndUserId(id,user.getId());}
        return articleRepo.findById(id).get();
    }
}
