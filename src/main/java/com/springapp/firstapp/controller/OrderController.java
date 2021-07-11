package com.springapp.firstapp.controller;

import com.springapp.firstapp.dto.OrderRequest;
import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.CustomerOrder;
import com.springapp.firstapp.module.OrderArticle;
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

    @PostMapping("")//valid
    public CustomerOrder createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    @DeleteMapping("/{id}")//valid
    public void deleteOrderById(@PathVariable Long id) {
        orderService.deleteOrderById(id);
    }

    @PutMapping("")//valid
    public CustomerOrder updateOrder(@RequestBody CustomerOrder customerOrder) {
        return orderService.updateOrder(customerOrder);
    }

    @GetMapping("")//valid
    public List<CustomerOrder> getAllOrder() {
        return orderService.getAllOrder();
    }

    @GetMapping("/{id}")//valid
    public Optional<CustomerOrder> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("state/{state}")//valid
    public List<CustomerOrder> getOrderByState(@PathVariable String state) {
        return orderService.getOrdersByState(state);
    }

    @GetMapping("modeOfPayment/{modeOfPayment}")//valid
    public List<CustomerOrder> getOrdersByModeOfPayment(@PathVariable String modeOfPayment) {
        return orderService.getOrdersByModeOfPayment(modeOfPayment);
    }

    @GetMapping("userHistory/{user}")//valid
    public List<CustomerOrder> getOrdersByUser(@PathVariable User user) {
        return orderService.getOrdersByUser(user);
    }


    @GetMapping("validateOrder/{id}")//valid
    public CustomerOrder ValidateOrder(@PathVariable Long id) {
        return orderService.ValidateOrder(id);
    }
}