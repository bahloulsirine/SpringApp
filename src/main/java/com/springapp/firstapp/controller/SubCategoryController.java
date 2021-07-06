package com.springapp.firstapp.controller;

import com.springapp.firstapp.module.Category;
import com.springapp.firstapp.module.SubCategory;
import com.springapp.firstapp.service.SubCategoryService;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/subCategory")
@AllArgsConstructor
public class SubCategoryController {
    private final SubCategoryService subCategoryService;

    @PostMapping("")
    public SubCategory createSubCategory(@RequestBody SubCategory subCategory){
        return subCategoryService.createCategory(subCategory);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        subCategoryService.deleteSubCategoryById(id);
    }

    @PutMapping("")
        public SubCategory updateSubCategory (@RequestBody SubCategory subCategory){
        return subCategoryService.updateSubCategory(subCategory);
    }

    @GetMapping("")
    public List<SubCategory> getAllSubCategories(){
        return subCategoryService.getAllSubCategory();
    }

    @GetMapping("/{id}")
    public Optional<SubCategory> getSubCategoryById(Long id){
        return subCategoryService.getSubCategoryById(id);
    }

    @GetMapping("name/{name")
    public SubCategory getSubCategoryByName(@PathVariable String name){
        return subCategoryService.getSubCategoryByName(name);
    }

    @GetMapping("category/category")
    public  List<SubCategory> getSubCategoriesByCategory(@PathVariable Category category){
        return subCategoryService.getSubCategoriesByCategory(category);
    }
}
