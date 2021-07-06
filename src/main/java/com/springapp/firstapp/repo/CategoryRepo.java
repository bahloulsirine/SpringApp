package com.springapp.firstapp.repo;

import com.springapp.firstapp.module.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo  extends JpaRepository<Category,Long> {
Category getCategoryByName(String name);
}
