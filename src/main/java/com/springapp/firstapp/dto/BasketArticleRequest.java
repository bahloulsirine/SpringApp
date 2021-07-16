package com.springapp.firstapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketArticleRequest{
    private Long basketArticleId ;
    private int amount;
}
