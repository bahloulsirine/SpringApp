package com.springapp.firstapp.controller;

import com.springapp.firstapp.module.Category;
import com.springapp.firstapp.module.SubCategory;
import com.springapp.firstapp.service.SubCategoryService;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/subCategory")
@AllArgsConstructor
public class SubCategoryController {
    private final SubCategoryService subCategoryService;
    @PreAuthorize("has_role(ADMIN)")
    @PostMapping("")//valid
    public SubCategory createSubCategory(@RequestBody SubCategory subCategory){
        return subCategoryService.createCategory(subCategory);
    }
    @PreAuthorize("has_role(ADMIN)")
    @DeleteMapping("/{id}")//valid
    public void deleteCategory(@PathVariable Long id){
        subCategoryService.deleteSubCategoryById(id);
    }

    @PreAuthorize("has_role(ADMIN)")
    @PutMapping("")//valid
        public SubCategory updateSubCategory (@RequestBody SubCategory subCategory){
        return subCategoryService.updateSubCategory(subCategory);
    }

    @GetMapping("")//valid
    public List<SubCategory> getAllSubCategories(){
        return subCategoryService.getAllSubCategory();
    }

    @GetMapping("/{id}")//valid
    public Optional<SubCategory> getSubCategoryById(@PathVariable Long id){
        return subCategoryService.getSubCategoryById(id);
    }

    @GetMapping("name/{name}")//valid
    public SubCategory getSubCategoryByName(@PathVariable String name){
        return subCategoryService.getSubCategoryByName(name);
    }

    @GetMapping("category/{category}")//valid
    public  List<SubCategory> getSubCategoriesByCategory(@PathVariable Category category){
        return subCategoryService.getSubCategoriesByCategory(category);
    }
}
