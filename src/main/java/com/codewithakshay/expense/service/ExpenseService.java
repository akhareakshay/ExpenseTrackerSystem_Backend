package com.codewithakshay.expense.service;

import com.codewithakshay.expense.dto.CreateExpenseRequest;
import com.codewithakshay.expense.dto.ExpenseResponse;
import com.codewithakshay.expense.entity.Expense;

import java.util.List;

public interface ExpenseService {

    ExpenseResponse createExpense(CreateExpenseRequest expenseRequest);

    List<ExpenseResponse> fetchAllExpenses();

    Expense fetchExpenseById(Long id);

    ExpenseResponse updateExpenseById(Long id, CreateExpenseRequest expenseRequest);

    void deleteExpenseById(Long id);

}
