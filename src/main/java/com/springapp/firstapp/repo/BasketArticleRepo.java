package com.springapp.firstapp.repo;

import com.springapp.firstapp.dto.BasketArticleRequest;
import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.BasketArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasketArticleRepo extends JpaRepository<BasketArticle,Long> {
    List<BasketArticle>findBasketArticlesByBasketId(Long id);

    @Override
    Optional<BasketArticle> findById(Long id);
    void deleteBasketArticleById(Long id);
    void deleteBasketArticlesByBasketId(Long id);
}
