package com.springapp.firstapp.service;

import com.springapp.firstapp.dto.BasketRequest;
import com.springapp.firstapp.dto.OrderItemRequest;
import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.Basket;
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
    public Basket updateBasket(Basket basket){
        
        return basketRepo.save(basket);
    }
    public void deleteBasket(Long id){
        basketRepo.deleteById(id);
    }

    public Basket createBasket(BasketRequest basketRequest){

        List<OrderItemRequest> orderItemRequests=basketRequest.getOrderItems();
        int price = getPrice(orderItemRequests);

        return basketRepo.save(new Basket(null,price,basketRequest.getUser()));

    }

    private int getPrice(List<OrderItemRequest> orderItemRequests) {
        int price=0;
        for (OrderItemRequest orderItemRequest: orderItemRequests){
           Long id=orderItemRequest.getArticle().getId();
           Article article=articleService.getArticleById(id).get();
            price+=orderItemRequest.getAmount()*article.getPrice();

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
