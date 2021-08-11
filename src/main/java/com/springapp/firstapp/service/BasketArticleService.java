package com.springapp.firstapp.service;

import com.springapp.firstapp.dto.BasketArticleRequest;
import com.springapp.firstapp.dto.OrderItemRequest;
import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.Basket;
import com.springapp.firstapp.module.BasketArticle;
import com.springapp.firstapp.module.User;
import com.springapp.firstapp.repo.BasketArticleRepo;
import com.springapp.firstapp.repo.BasketRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class BasketArticleService {
    private final BasketArticleRepo basketArticleRepo;
    private  final BasketService basketService;
    private final BasketRepo basketRepo;
    private  final ArticleService articleService;
    public List<BasketArticle> getArticlesByBasketId(Long id){
        return
                basketArticleRepo.findBasketArticlesByBasketId(id);
    }

    public BasketArticle update(BasketArticleRequest basketArticleRequest) {
        BasketArticle basketArticle=basketArticleRepo.findById(basketArticleRequest.getBasketArticleId()).get();
        Long basketId=basketArticle.getBasket().getId();
        Basket basket=basketRepo.findById(basketId).get();
        basketArticle.setAmount(basketArticleRequest.getAmount());

        List<BasketArticle> basketArticles=getArticlesByBasketId(basketId);
        basket.setPricesSum(getPrice(basketArticles));
        basketService.updateBasket(basket);
        return  basketArticleRepo.save(basketArticle);

    }
        public  int getPrice(List<BasketArticle> basketArticles) {
            int price=0;
            for (BasketArticle basketArticle: basketArticles){
                Long id=basketArticle.getArticle().getId();
                Article article=articleService.getArticleById(id).get();
                price+=basketArticle.getAmount()*article.getPrice();
            }
            return price;
        }

    public void deleteBasketArticle(Long id) {
        BasketArticle basketArticle=basketArticleRepo.findById(id).get();
        Long basketId=basketArticle.getBasket().getId();
        Basket basket=basketRepo.findById(basketId).get();
        basketArticleRepo.deleteBasketArticleById(id);
        List<BasketArticle> basketArticles=getArticlesByBasketId(basketId);
        basket.setPricesSum(getPrice(basketArticles));
        basketService.updateBasket(basket);
    }
    public List<BasketArticle>getBasketArticlesByBasketId(Long id){
        return basketArticleRepo.getBasketArticlesByBasketId(id);
    }

    public BasketArticle createBasketArticle(int amount, Long id,User user) {

        Article article=articleService.getArticleById(id).get();
        Basket basket=basketService.getBasketByUser(user);
        List<BasketArticle> basketArticles=getArticlesByBasketId(basket.getId());

        for (BasketArticle basketArticle:basketArticles){
            if(basketArticle.getArticle()==article){
                BasketArticleRequest basketArticleRequest=new BasketArticleRequest(basketArticle.getId(),basketArticle.getAmount()+amount);
                return update(basketArticleRequest);
            }
        }

        BasketArticle basketArticle=new BasketArticle(null,amount,basket,article);
        basketArticleRepo.save(basketArticle);
        List<BasketArticle> basketArticlesUpdating=getArticlesByBasketId(basket.getId());
        basket.setPricesSum(getPrice(basketArticlesUpdating));
        basketService.updateBasket(basket);
        return  basketArticle;
    }
}
