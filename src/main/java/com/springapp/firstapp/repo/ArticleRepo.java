package com.springapp.firstapp.repo;

import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.Category;
import com.springapp.firstapp.module.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepo extends JpaRepository<Article,Long> {

    List<Article> getArticlesByCategoryId(Long id);
    List<Article> getArticlesBySubCategoryId(Long id);
    List<Article> getArticlesByColor(String color);
    Article getArticleByCode(Long code);
    List<Article> getArticlesByPrice(int price);
    List<Article>getArticlesByCategoryName(String name);
    List<Article>getArticlesBySubCategoryName(String name);
}
