package com.springapp.firstapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteRecommendationRequest {
    private Long articleId;
    private Long recommendationId;
}
