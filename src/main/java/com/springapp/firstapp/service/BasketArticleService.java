package com.springapp.firstapp.service;

import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.BasketArticle;
import com.springapp.firstapp.repo.BasketArticleRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BasketArticleService {
    private final BasketArticleRepo basketArticleRepo;
    public List<Article> getArticlesByBasketId(Long id){
        return basketArticleRepo.findBasketArticlesByBasketId(id);
    }
}
