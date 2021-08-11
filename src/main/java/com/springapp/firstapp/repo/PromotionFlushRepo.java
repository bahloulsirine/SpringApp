package com.springapp.firstapp.repo;

import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.Promotion;
import com.springapp.firstapp.module.PromotionFlush;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface PromotionFlushRepo extends JpaRepository<PromotionFlush,Long> {

        PromotionFlush getPromotionFlushByPercentagePromotion(int percentage);
        void deletePromotionFlushByPercentagePromotion(int percentage);
        List<PromotionFlush> getPromotionFlushesByPromotionExpirationAfter(Date date);
        void deletePromotionFlushesByPromotionExpirationBefore(Date date);
    Set<PromotionFlush>getPromotionFlushesByArticlesIn(List<Article> articles);
    }

