package com.springapp.firstapp.repo;

import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.BasketArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketArticleRepo extends JpaRepository<BasketArticle,Long> {
    List<Article>findBasketArticlesByBasketId(Long id);
}
