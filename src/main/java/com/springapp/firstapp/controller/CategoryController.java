package com.springapp.firstapp.controller;

import com.springapp.firstapp.module.Category;
import com.springapp.firstapp.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/category")

@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("")//valid
    public Category createCategory (@RequestBody Category category){
        return categoryService.createCategory(category);
    }
    @DeleteMapping("/{id}")//valid
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
    }
    @PutMapping("")//valid
    public Category updateCategory (@RequestBody Category category){
        return categoryService.updateCategory(category);
    }
    @GetMapping("/{id}")//valid
    public Optional<Category> getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    @GetMapping("")//valid
    public List<Category> getAllCategories(){
        return categoryService.getAllCategory();
    }
    @GetMapping("name/{name}")//valid
        public Category getCategoryByName(@PathVariable String name){
        return categoryService.getCategoryByName(name);
    }

}
