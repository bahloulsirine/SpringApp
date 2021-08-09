package com.springapp.firstapp.controller;

import com.springapp.firstapp.module.OrderArticle;
import com.springapp.firstapp.service.OrderProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orderArticle")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderArticleController {
    private final OrderProductService orderProductService;
@GetMapping("/{id}")//valid
    public List<OrderArticle>getOrderArticlesByOrderId(@PathVariable Long id){
    return orderProductService.getOrderArticlesByOrderId(id);
}
}
