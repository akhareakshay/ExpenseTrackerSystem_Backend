package com.codewithakshay.expense.controller;

import com.codewithakshay.expense.dto.CreateExpenseRequest;
import com.codewithakshay.expense.dto.ExpenseResponse;
import com.codewithakshay.expense.entity.Expense;
import com.codewithakshay.expense.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ExpenseResponse> createExpense(@Valid @RequestBody CreateExpenseRequest request) {

        return ResponseEntity.ok(expenseService.createExpense(request));
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> fetchAllExpenses() {

        return ResponseEntity.ok(expenseService.fetchAllExpenses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> fetchExpenseById(@PathVariable Long id) {

        return ResponseEntity.ok(expenseService.fetchExpenseById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponse> updateExpenseById(@PathVariable Long id, @Valid @RequestBody CreateExpenseRequest expenseRequest) {

        return ResponseEntity.ok(expenseService.updateExpenseById(id, expenseRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpenseById(@PathVariable Long id) {

        expenseService.deleteExpenseById(id);
        return ResponseEntity.noContent().build();
    }


}
