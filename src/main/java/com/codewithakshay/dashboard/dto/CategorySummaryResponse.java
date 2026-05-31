package com.codewithakshay.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class CategorySummaryResponse {

    private String category;
    private BigDecimal amount;

}
