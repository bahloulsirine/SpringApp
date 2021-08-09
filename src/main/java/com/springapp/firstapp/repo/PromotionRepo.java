package com.springapp.firstapp.repo;

import com.springapp.firstapp.module.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PromotionRepo extends JpaRepository<Promotion,Long> {
    Promotion getPromotionByPercentagePromotion(int percentage);
    void deletePromotionByPercentagePromotion(int percentage);

}
