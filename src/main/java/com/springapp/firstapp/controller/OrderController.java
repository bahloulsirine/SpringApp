package com.springapp.firstapp.controller;

import com.springapp.firstapp.dto.OrderRequest;
import com.springapp.firstapp.module.CustomerOrder;
import com.springapp.firstapp.module.User;
import com.springapp.firstapp.repo.OrderRepo;
import com.springapp.firstapp.repo.UserRepo;
import com.springapp.firstapp.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/order")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserRepo userRepo;
    private final OrderRepo orderRepo;


//    @PostMapping("")//valid
//    public CustomerOrder createOrder(@RequestBody OrderRequest orderRequest) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return orderService.createOrder(orderRequest, user);
//    }
@PostMapping("")//valid
public CustomerOrder createOrder(@RequestBody String modeOfPayment) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return orderService.createOrder(modeOfPayment, user);
}

    
    @DeleteMapping("/{id}")//valid
    public void deleteOrderById(@PathVariable Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderService.deleteOrderById(id, user);
    }

    @PreAuthorize("hasRole('ADMIN')")
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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.hasRole("USER")) {
            return orderRepo.getOrdersByStateAndUserId(state, user.getId());
        } else {
            return orderService.getOrdersByState(state);
        }
    }

    @GetMapping("modeOfPayment/{modeOfPayment}")//valid//inutile
    public List<CustomerOrder> getOrdersByModeOfPayment(@PathVariable String modeOfPayment) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.hasRole("USER")) {
            return orderRepo.getCustomerOrderByModeOfPaymentAndUserId(modeOfPayment, user.getId());
        } else {
            return orderService.getOrdersByModeOfPayment(modeOfPayment);
        }
    }

    @GetMapping("/userHistory")//valid
    public List<CustomerOrder> getOrdersByUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return orderService.getOrdersByUserId(user.getId());
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("validateOrder/{id}/{state}")//valid
    public CustomerOrder ValidateOrder(@PathVariable Long id, @PathVariable boolean state) {
        if (state) {
            return orderService.ValidateOrder(id);
        } else {
            return orderService.RefuseOrder(id);
        }


    }
}