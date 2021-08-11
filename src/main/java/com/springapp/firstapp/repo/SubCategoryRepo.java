package com.springapp.firstapp.repo;

import com.springapp.firstapp.module.Category;
import com.springapp.firstapp.module.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepo extends JpaRepository<SubCategory,Long> {
    SubCategory getSubCategoryByName(String name);
    List<SubCategory> getSubCategoriesByCategory(Category category);
    List<SubCategory>getSubCategoriesByCategoryId(Long id);
}
