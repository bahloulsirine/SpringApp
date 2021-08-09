package com.springapp.firstapp.repo;

import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.CustomerOrder;
import com.springapp.firstapp.module.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<CustomerOrder,Long> {
    List<CustomerOrder> getOrdersByModeOfPayment(String modeOfPayment);
    List<CustomerOrder> getOrdersByState(String state);
    List<CustomerOrder>getCustomerOrderByModeOfPaymentAndUserId(String modeOfPayment,Long id);
    List<CustomerOrder> getOrdersByStateAndUserId(String state,Long id);
    List<CustomerOrder>getOrdersByUserId(Long id);
    void deleteCustomerOrderById(Long id);
}
