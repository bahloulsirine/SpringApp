package com.springapp.firstapp.service;

import com.springapp.firstapp.dto.SubcategoryCreateRequest;
import com.springapp.firstapp.module.Category;
import com.springapp.firstapp.module.SubCategory;
import com.springapp.firstapp.repo.CategoryRepo;
import com.springapp.firstapp.repo.SubCategoryRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SubCategoryService {
    private final SubCategoryRepo subCategoryRepo;
    private final CategoryService categoryService;

    public SubCategory createSubcategory(SubcategoryCreateRequest subCategory) {
        Category category=categoryService.getCategoryById(subCategory.getCategoryId()).get();
        SubCategory subCategory1=new SubCategory(null,subCategory.getName(),category);
        return subCategoryRepo.save(subCategory1);
    }

    public void deleteSubCategoryById(Long id) {
        subCategoryRepo.deleteById(id);
    }

    public SubCategory updateSubCategory(SubCategory subCategory) {
        return subCategoryRepo.save(subCategory);
    }

    public List<SubCategory> getAllSubCategory() {
        return subCategoryRepo.findAll();
    }

    public Optional<SubCategory> getSubCategoryById(Long id) {
        return subCategoryRepo.findById(id);
    }

    public SubCategory getSubCategoryByName(String name) {
        return subCategoryRepo.getSubCategoryByName(name);
    }

    public List<SubCategory> getSubCategoriesByCategory(Category category) {
        return subCategoryRepo.getSubCategoriesByCategory(category);
    }
}
