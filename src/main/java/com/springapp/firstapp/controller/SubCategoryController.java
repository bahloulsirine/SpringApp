package com.springapp.firstapp.controller;

import com.springapp.firstapp.dto.CategoryRequest;
import com.springapp.firstapp.dto.SubcategoryCreateRequest;
import com.springapp.firstapp.module.Category;
import com.springapp.firstapp.module.SubCategory;
import com.springapp.firstapp.repo.CategoryRepo;
import com.springapp.firstapp.service.SubCategoryService;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/subcategory")
@AllArgsConstructor
@CrossOrigin (origins = "*",allowedHeaders = "*")

public class SubCategoryController {
    private final SubCategoryService subCategoryService;
    private final CategoryRepo categoryRepo;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")//valid
    public SubCategory createSubCategory(@RequestBody SubcategoryCreateRequest subCategory){
        return subCategoryService.createSubcategory(subCategory);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")//valid
    public void deleteSubcategory(@PathVariable Long id){
        subCategoryService.deleteSubCategoryById(id);
    }

    @PreAuthorize("hasRole(ADMIN)")
    @PutMapping("")//valid
        public SubCategory updateSubCategory (@RequestBody SubCategory subCategory){
        return subCategoryService.updateSubCategory(subCategory);
    }

    @GetMapping("")//valid//inutile
    public List<SubCategory> getAllSubCategories(){
        return subCategoryService.getAllSubCategory();
    }

    @GetMapping("/{id}")//valid//inutile
    public Optional<SubCategory> getSubCategoryById(@PathVariable Long id){
        return subCategoryService.getSubCategoryById(id);
    }

    @GetMapping("/name/{name}")//valid//inutile
    public SubCategory getSubCategoryByName(@PathVariable String name){
        return subCategoryService.getSubCategoryByName(name);
    }

    @GetMapping("/category/{category}")//valid
    public  List<SubCategory> getSubCategoriesByCategory(@PathVariable String category){
        return subCategoryService.getSubCategoriesByCategory(categoryRepo.getCategoryByName(category));
    }
    @GetMapping("/categoryId/{id}")//valid
    public  List<SubCategory> getSubCategoriesByCategoryId(@PathVariable Long id){
        return subCategoryService.getSubcategoryByCategoryId(id);
    }
    @GetMapping("categoryRequest")
    public List<CategoryRequest> getCategoryRequest(){
        return subCategoryService.getCategoryRequest();
    }
}
