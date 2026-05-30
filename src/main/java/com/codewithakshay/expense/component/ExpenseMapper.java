package com.codewithakshay.expense.component;

import com.codewithakshay.expense.dto.ExpenseResponse;
import com.codewithakshay.expense.entity.Expense;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {

    public static ExpenseResponse mapToResponse(Expense expense) {

        return ExpenseResponse.builder()
                .id(expense.getId())
                .title(expense.getTitle())
                .amount(expense.getAmount())
                .category(expense.getCategory())
                .description(expense.getDescription())
                .expenseDate(expense.getExpenseDate())
                .build();
    }

}
