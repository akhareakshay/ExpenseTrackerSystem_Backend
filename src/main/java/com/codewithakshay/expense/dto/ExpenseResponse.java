package com.codewithakshay.expense.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class ExpenseResponse {

    private Long id;
    private String title;
    private BigDecimal amount;
    private String description;
    private String category;
    private LocalDate expenseDate;

}
