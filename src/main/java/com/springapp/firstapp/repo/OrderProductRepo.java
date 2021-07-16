package com.springapp.firstapp.repo;

import com.springapp.firstapp.module.OrderArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderProductRepo extends JpaRepository<OrderArticle,Long> {
    List<OrderArticle> findOrderArticlesByCustomerOrderId(Long id);
    void deleteOrderArticlesByCustomerOrderId(Long id);
}
