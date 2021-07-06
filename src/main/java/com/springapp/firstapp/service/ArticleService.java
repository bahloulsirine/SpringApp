package com.springapp.firstapp.service;

import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.Category;
import com.springapp.firstapp.module.SubCategory;
import com.springapp.firstapp.repo.ArticleRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticleService {
    private final  ArticleRepo articleRepo;

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

    public List <Article> getArticlesByCategoryId(Long id){
        return articleRepo.getArticlesByCategoryId(id);
    }
    public List <Article> getArticlesBySubcategoryId(Long id){
        return articleRepo.getArticlesBySubCategoryId(id);
    }
    public List <Article> getArticlesByColor(String color){
        return articleRepo.getArticlesByColor(color);
    }
    public Article getArticleByCode(Long code){ return articleRepo.getArticleByCode(code);}
    public List<Article>getArticleByPrice(int price){return articleRepo.getArticlesByPrice(price);}
    public List<Article>getArticlesByCategoryName(String name){return articleRepo.getArticlesByCategoryName(name);}
    public List<Article>getArticlesBySubCategoryName(String name){return articleRepo.getArticlesBySubCategoryName(name);}
}
