package com.springapp.firstapp.service;

import com.springapp.firstapp.dto.OrderItemRequest;
import com.springapp.firstapp.dto.OrderRequest;
import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.CustomerOrder;
import com.springapp.firstapp.module.OrderArticle;
import com.springapp.firstapp.module.User;
import com.springapp.firstapp.repo.OrderProductRepo;
import com.springapp.firstapp.repo.OrderRepo;
import com.springapp.firstapp.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepo orderRepo;
    private final OrderProductRepo orderProductRepo;
    private  final  ArticleService articleService ;
    private final UserRepo userRepo;

    public CustomerOrder createOrder(OrderRequest orderRequest){
        CustomerOrder customerOrder  = new CustomerOrder(null,0,orderRequest.getModeOfPayment(),"Waiting",orderRequest.getUser());
        customerOrder  = orderRepo.save(customerOrder);
        int price  = 0 ;
        for(OrderItemRequest orderItem : orderRequest.getOrderItems()){
            OrderArticle orderArticle = new OrderArticle(null,orderItem.getAmount(),customerOrder,orderItem.getArticle());
            Article article = articleService.getArticleById(orderArticle.getArticle().getId()).get();
            price += article.getPrice() * orderItem.getAmount();
            orderProductRepo.save(orderArticle);
        }
        customerOrder.setPrice(price);
        return orderRepo.save(customerOrder);
    }

    public void deleteOrderById(Long id){
        orderRepo.deleteById(id);
    }

    public List<CustomerOrder> getAllOrder(){
        return orderRepo.findAll();
    }
    public Optional<CustomerOrder> getOrderById(Long id){
        return orderRepo.findById(id);
    }
    public List<CustomerOrder> getOrdersByState(String state){
        return orderRepo.getOrdersByState(state);
    }
    public  List<CustomerOrder>getOrdersByModeOfPayment(String modeOfPayment){
        return orderRepo.getOrdersByModeOfPayment(modeOfPayment);
    }
    public List<CustomerOrder> getOrdersByUser(User user){
        return orderRepo.getOrdersByUser(user);
    }

    public CustomerOrder ValidateOrder(Long id){
        CustomerOrder order=getOrderById(id).orElseThrow();
        List<OrderArticle> orderArticles = orderProductRepo.findOrderArticlesByCustomerOrderId(id) ;

        for (OrderArticle orderArticle:orderArticles) {
            Article article = orderArticle.getArticle();
            article.setStock(article.getStock() -  orderArticle.getAmount() );
            articleService.updateArticle(article);
        }
        User user=order.getUser();
        int total=user.getTotalOrder();

        System.out.println(total);
        user.setTotalOrder(total+order.getPrice());
        userRepo.save(user);
        order.setState("Accepted");
        return  orderRepo.save(order);
    }
}