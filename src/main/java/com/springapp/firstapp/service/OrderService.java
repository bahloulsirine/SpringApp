package com.springapp.firstapp.service;

import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.CustomerOrder;
import com.springapp.firstapp.module.User;
import com.springapp.firstapp.repo.OrderRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;
    public CustomerOrder createOrder(CustomerOrder customerOrder){
        return orderRepo.save(customerOrder);
    }
    public void deleteOrderById(Long id){
        orderRepo.deleteById(id);
    }
    public CustomerOrder updateOrder(CustomerOrder customerOrder){
        return orderRepo.save(customerOrder);
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
    public List<CustomerOrder> getOrdersByArticle(Article article){
        return orderRepo.getOrdersByArticle(article);
    }
}