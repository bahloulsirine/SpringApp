package com.springapp.firstapp.service;

import com.springapp.firstapp.dto.BasketRequest;
import com.springapp.firstapp.dto.OrderItemRequest;
import com.springapp.firstapp.module.*;
import com.springapp.firstapp.repo.BasketArticleRepo;
import com.springapp.firstapp.repo.BasketRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class BasketService {
    private final BasketRepo basketRepo;
    private final ArticleService articleService;
    private  final BasketArticleRepo basketArticleRepo;
    public Basket updateBasket(Basket basket){
        return basketRepo.save(basket);
    }
    public void deleteBasket(Long id){
        basketArticleRepo.deleteBasketArticlesByBasketId(id);
        Basket basket=basketRepo.findById(id).get();
        basket.setPricesSum(0);
        System.out.println("**********************************");
        System.out.println(basket.getPricesSum());
        basketRepo.save(basket);
    }

    public Basket createBasket(BasketRequest basketRequest,User user){
        Basket basket=new Basket(null,0,user);
        basket=basketRepo.save(basket);
        List<OrderItemRequest> orderItemRequests=basketRequest.getOrderItems();
        int price = getPrice(orderItemRequests,basket);
        basket.setPricesSum(price);
        return basketRepo.save(basket);
    }
    private int getPrice(List<OrderItemRequest> orderItemRequests,Basket basket) {
        int price=0;
        for (OrderItemRequest orderItemRequest: orderItemRequests){
            BasketArticle basketArticle=new BasketArticle(null,orderItemRequest.getAmount(),basket,orderItemRequest.getArticle());
           Long id=orderItemRequest.getArticle().getId();
           Article article=articleService.getArticleById(id).get();
            price+=orderItemRequest.getAmount()*article.getPrice();
            basketArticleRepo.save(basketArticle); }
        return price;}
    public Basket getBasketByUser(User user){
        return basketRepo.findBasketByUser(user);
    }
}
