package com.springapp.firstapp.repo;

import com.springapp.firstapp.module.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepo extends JpaRepository<Article,Long> {

    List<Article> getArticlesBySubCategoryId(Long id);

    List<Article> getArticlesByColor(String color);

    Article getArticleByCode(Long code);
    List<Article>getArticlesByName(String name);
    Article getArticleByUrl(String url);

    List<Article> getArticlesByPrice(int price);


    List<Article> getArticlesBySubCategoryName(String name);

    List<Article> getArticlesByStockLessThan(int stock);

    List<Article> getArticlesByStockLessThanAndUser(int stock, User user);
    List<Article>getArticlesByUserId(Long id);
    List<Article> getArticlesByIdIn(List<Long> ids);

    @Query(value = "SELECT DISTINCT user_id FROM article WHERE stock < ?1 ", nativeQuery = true)
    List<Long> getUserIdInsufficientStock(int stoke);


    @Modifying
    @Query(value = "DELETE FROM `article_articles` WHERE article_id = ?1 AND articles_id =?2  ",nativeQuery = true)
    void deleteRecommendations(Long articleId,Long recommendationId);
}
