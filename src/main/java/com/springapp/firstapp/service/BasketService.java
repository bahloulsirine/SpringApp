package com.springapp.firstapp.service;

import com.springapp.firstapp.dto.BasketRequest;
import com.springapp.firstapp.dto.OrderItemRequest;
import com.springapp.firstapp.dto.OrderRequest;
import com.springapp.firstapp.module.*;
import com.springapp.firstapp.repo.BasketArticleRepo;
import com.springapp.firstapp.repo.BasketRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BasketService {
    private final BasketRepo basketRepo;
    private final ArticleService articleService;
    private  final BasketArticleRepo basketArticleRepo;
    public Basket updateBasket(Basket basket){
        return basketRepo.save(basket);
    }
    public void deleteBasket(Long id){
        basketRepo.deleteById(id);
    }

    public Basket createBasket(BasketRequest basketRequest){
        Basket basket=new Basket(null,0,basketRequest.getUser());
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
            basketArticleRepo.save(basketArticle);

        }
        return price;
    }

    public Optional<Basket> getBasketById(Long id){
        return basketRepo.findById(id);
    }

  public List<Basket> getAllBasket(){
        return basketRepo.findAll();
  }

}
