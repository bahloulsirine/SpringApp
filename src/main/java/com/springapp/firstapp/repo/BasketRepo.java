package com.springapp.firstapp.repo;

import com.springapp.firstapp.module.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepo extends JpaRepository<Basket,Long> {

}
