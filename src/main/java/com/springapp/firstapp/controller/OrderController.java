package com.springapp.firstapp.controller;

import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.CustomerOrder;
import com.springapp.firstapp.module.User;
import com.springapp.firstapp.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/order")

@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("")
    public CustomerOrder createOrder(@RequestBody CustomerOrder customerOrder){
        return orderService.createOrder(customerOrder);
    }
    @DeleteMapping ("/{id}")
    public void deleteOrderById(@PathVariable Long id){
        orderService.deleteOrderById(id);
    }

    @PutMapping ("")
    public CustomerOrder updateOrder(@RequestBody CustomerOrder customerOrder){
        return orderService.updateOrder(customerOrder);
    }
    @GetMapping("")
    public List<CustomerOrder> getAllOrder(){
        return orderService.getAllOrder();
    }

    @GetMapping("/{id}")
    public Optional<CustomerOrder> getOrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }

    @GetMapping("state/{state}")
    public List<CustomerOrder> getOrderByState(@PathVariable String state){
        return orderService.getOrdersByState(state);
    }

    @GetMapping("modeOfPayment/{modeOfPayment}")
    public List<CustomerOrder> getOrdersByModeOfPayment(@PathVariable String modeOfPayment){
        return orderService.getOrdersByModeOfPayment(modeOfPayment);
    }

    @GetMapping("user/{user}")
    public List<CustomerOrder> getOrdersByUser(@PathVariable User user){
        return orderService.getOrdersByUser(user);
    }

    @GetMapping("article/{article}")
    public List<CustomerOrder> getOrdersByArticle(@PathVariable Article article){
        return orderService.getOrdersByArticle(article);
    }
}
