package com.springapp.firstapp.service;

import com.springapp.firstapp.dto.OrderItemRequest;
import com.springapp.firstapp.dto.OrderRequest;
import com.springapp.firstapp.module.*;
import com.springapp.firstapp.repo.OrderProductRepo;
import com.springapp.firstapp.repo.OrderRepo;
import com.springapp.firstapp.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.ArrayList;
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
    private final  OrderProductService orderProductService;
    private final BasketService basketService;
    private final BasketArticleService basketArticleService;

//    public CustomerOrder createOrder(OrderRequest orderRequest,User user){
//
//        CustomerOrder customerOrder  = new CustomerOrder(null,0,orderRequest.getModeOfPayment(),"Waiting",user);
//        customerOrder  = orderRepo.save(customerOrder);
//        int price  = 0 ;
//        for(OrderItemRequest orderItem : orderRequest.getOrderItems()){
//            OrderArticle orderArticle = new OrderArticle(null,orderItem.getAmount(),customerOrder,orderItem.getArticle());
//            Article article = articleService.getArticleById(orderArticle.getArticle().getId()).get();
//            price += article.getPrice() * orderItem.getAmount();
//            orderProductRepo.save(orderArticle);
//        }
//        customerOrder.setPrice(price);
//        return orderRepo.save(customerOrder);
//    }
public CustomerOrder createOrder(String modeOfPayment,User user){
    List<OrderItemRequest> orderItems = new ArrayList<>();
    Basket basket= basketService.getBasketByUser(user);
    List<BasketArticle> basketArticles=basketArticleService.getBasketArticlesByBasketId(basket.getId());
    for(BasketArticle basketArticle:basketArticles){
        OrderItemRequest orderItemRequest=new OrderItemRequest(basketArticle.getAmount(),basketArticle.getArticle());
        orderItems.add(orderItemRequest);
    }
    CustomerOrder customerOrder  = new CustomerOrder(null,0,modeOfPayment,"Waiting",user);
    customerOrder  = orderRepo.save(customerOrder);
    int price  = 0 ;
    for(OrderItemRequest orderItem : orderItems){
        OrderArticle orderArticle = new OrderArticle(null,orderItem.getAmount(),customerOrder,orderItem.getArticle());
        Article article = articleService.getArticleById(orderArticle.getArticle().getId()).get();
        price += article.getPrice() * orderItem.getAmount();
        orderProductRepo.save(orderArticle);
    }
    customerOrder.setPrice(price);
    basketService.deleteBasket(basket.getId());
    return orderRepo.save(customerOrder);
}
    public void deleteOrderById(Long id,User user){
        List<CustomerOrder> orders=getOrdersByUserId(user.getId());
        CustomerOrder order=getOrderById(id).get();
        if (orders.contains(order)){
        String  state=order.getState();
       if (state.equals("Waiting")){
           orderProductRepo.deleteOrderArticlesByCustomerOrderId(id);
           orderRepo.deleteCustomerOrderById(id);}}
    }

    public List<CustomerOrder> getAllOrders(){
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
    public List<CustomerOrder> getOrdersByUserId(Long id){
        return orderRepo.getOrdersByUserId(id);
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

    public CustomerOrder RefuseOrder(Long id) {
        CustomerOrder order=getOrderById(id).orElseThrow();
        order.setState("Refused");
        return  orderRepo.save(order);
    }
}