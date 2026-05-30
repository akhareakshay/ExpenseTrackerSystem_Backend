package com.codewithakshay.expense.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CreateExpenseRequest {

    @NotBlank
    private String title;

    @NotNull
    private BigDecimal amount;

    private String description;

    @NotBlank
    private String category;

    @NotNull
    private LocalDate expenseDate;

}
