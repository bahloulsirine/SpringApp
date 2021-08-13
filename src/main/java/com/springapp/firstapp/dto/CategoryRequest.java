package com.springapp.firstapp.dto;

import com.springapp.firstapp.module.Category;
import com.springapp.firstapp.module.SubCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
    Category category;
    List<SubCategory> subCategories;
}
