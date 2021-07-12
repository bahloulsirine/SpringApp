package com.springapp.firstapp.service;

import com.springapp.firstapp.module.OrderArticle;
import com.springapp.firstapp.repo.OrderProductRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class OrderProductService {
    private final OrderProductRepo orderProductRepo;
    public List<OrderArticle>getOrderArticlesByOrderId(Long id){
         return orderProductRepo.findOrderArticlesByCustomerOrderId(id);

    }
    public void deleteOrderProduct(Long id){
        orderProductRepo.deleteOrderArticleById(id);
    }
}

