package com.springapp.firstapp.service;

import com.springapp.firstapp.dto.CategoryRequest;
import com.springapp.firstapp.module.Category;
import com.springapp.firstapp.module.SubCategory;
import com.springapp.firstapp.repo.CategoryRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepo categoryRepo;

    public Category createCategory(Category category){
        return categoryRepo.save(category);
    }
    public void deleteCategoryById(Long id){
        categoryRepo.deleteById(id);
    }
    public Category updateCategory(Category category){
        return categoryRepo.save(category);
    }
    public Optional<Category> getCategoryById(Long id){
        return categoryRepo.findById(id);
    }
    public List<Category> getAllCategory(){
        return categoryRepo.findAll();
    }
    public Category getCategoryByName(String name){ return categoryRepo.getCategoryByName(name); }

}
