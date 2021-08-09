package com.springapp.firstapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubcategoryCreateRequest {
    private String name;
    private Long categoryId;
}
