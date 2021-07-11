package com.springapp.firstapp.dto;

import com.springapp.firstapp.module.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequest {
    private int amount;
    private Article article;
}
