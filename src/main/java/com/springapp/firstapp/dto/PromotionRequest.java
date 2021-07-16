package com.springapp.firstapp.dto;

import com.springapp.firstapp.module.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionRequest {
    private Date expirationDate;
    private  int percentage;
    private List<Article> articles;

}
