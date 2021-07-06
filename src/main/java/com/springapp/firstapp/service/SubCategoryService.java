package com.springapp.firstapp.service;

import com.springapp.firstapp.module.Category;
import com.springapp.firstapp.module.SubCategory;
import com.springapp.firstapp.repo.SubCategoryRepo;
import lombok.AllArgsConstructor;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SubCategoryService {
    private final SubCategoryRepo subCategoryRepo;

    public SubCategory createCategory(SubCategory subCategory) {
        return subCategoryRepo.save(subCategory);
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
