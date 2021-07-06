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
    @PostMapping("")
    public Category createCategory (@RequestBody Category category){
        return categoryService.createCategory(category);
    }
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
    }
    @PutMapping("")
    public Category updateCategory (@RequestBody Category category){
        return categoryService.updateCategory(category);
    }
    @GetMapping("/{id}")
    public Optional<Category> getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    @GetMapping("")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategory();
    }
    @GetMapping("name/{name}")
        public Category getCategoryByName(@PathVariable String name){
        return categoryService.getCategoryByName(name);
    }

}
