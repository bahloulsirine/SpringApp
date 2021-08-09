package com.springapp.firstapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springapp.firstapp.module.Article;
import com.springapp.firstapp.module.SubCategory;
import com.springapp.firstapp.module.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleUpdateRequest {
   private Long id;
    private int stock;
    private int TVA;
    private String description;
    private String color;
    private int weight;
    private int price;
    private String url;
    private String name;
}
