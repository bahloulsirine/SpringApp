package com.springapp.firstapp.controller;

import com.springapp.firstapp.dto.OrderRequest;
import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.CustomerOrder;
import com.springapp.firstapp.module.OrderArticle;
import com.springapp.firstapp.module.User;
import com.springapp.firstapp.repo.UserRepo;
import com.springapp.firstapp.service.OrderService;
import com.springapp.firstapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/order")

@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private  final UserRepo userRepo;



    @PostMapping("")//valid
    public CustomerOrder createOrder(@RequestBody OrderRequest orderRequest) {
        String mail  = SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepo.getUserByEmail(mail);
        return orderService.createOrder(orderRequest,user);
    }

    @DeleteMapping("/{id}")//valid
    public void deleteOrderById(@PathVariable Long id) {
        orderService.deleteOrderById(id);
    }

    @GetMapping("")//valid
    public List<CustomerOrder> getAllOrder() {
        return orderService.getAllOrders();
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

    @GetMapping("/userHistory/{id}")//valid
    public List<CustomerOrder> getOrdersByUser(@PathVariable Long id) {
        User user  = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user.hasRole("ADMIN")){
        return orderService.getOrdersByUserId(id);
        }else{
            return orderService.getOrdersByUserId(user.getId());

        }

    }


    @GetMapping("validateOrder/{id}")//valid
    public CustomerOrder ValidateOrder(@PathVariable Long id) {
        return orderService.ValidateOrder(id);
    }
}