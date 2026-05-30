package com.codewithakshay.expense.service;

import com.codewithakshay.expense.component.ExpenseMapper;
import com.codewithakshay.expense.dto.CreateExpenseRequest;
import com.codewithakshay.expense.dto.ExpenseResponse;
import com.codewithakshay.expense.entity.Expense;
import com.codewithakshay.expense.exception.ResourceNotFoundException;
import com.codewithakshay.expense.repository.ExpenseRepository;
import com.codewithakshay.user.entity.User;
import com.codewithakshay.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements  ExpenseService{

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    public ExpenseResponse createExpense(CreateExpenseRequest request) {

        User currentUser = getCurrentUser();

        Expense expense = Expense.builder()
                .title(request.getTitle())
                .amount(request.getAmount())
                .description(request.getDescription())
                .category(request.getCategory())
                .expenseDate(request.getExpenseDate())
                .user(currentUser)
                .build();

        Expense savedExpense = expenseRepository.save(expense);

        return ExpenseMapper.mapToResponse(savedExpense);
    }

    @Override
    public List<ExpenseResponse> fetchAllExpenses() {
        User currentUser = getCurrentUser();

        return expenseRepository.findByUser(currentUser)
                .stream()
                .map(ExpenseMapper::mapToResponse)
                .toList();
    }

    @Override
    public Expense fetchExpenseById(Long id) {
        User currentUser = getCurrentUser();

        return expenseRepository.findByIdAndUser(id, currentUser).orElseThrow(() -> new ResourceNotFoundException("Expense not found !"));
    }

    @Override
    public ExpenseResponse updateExpenseById(Long id, CreateExpenseRequest expenseRequest) {
        User currentUser = getCurrentUser();

        Expense userExpense = expenseRepository.findByIdAndUser(id, currentUser).orElseThrow(() -> new ResourceNotFoundException("Expense not found !"));

        userExpense.setTitle(expenseRequest.getTitle());
        userExpense.setCategory(expenseRequest.getCategory());
        userExpense.setAmount(expenseRequest.getAmount());
        userExpense.setDescription(expenseRequest.getDescription());
        userExpense.setExpenseDate(expenseRequest.getExpenseDate());

        Expense expenseUpdated = expenseRepository.save(userExpense);

        return ExpenseMapper.mapToResponse(expenseUpdated);
    }

    @Override
    public void deleteExpenseById(Long id) {

        User currentUser = getCurrentUser();
        Expense expense = expenseRepository.findByIdAndUser(id, currentUser).orElseThrow(() -> new ResourceNotFoundException("Expense not found !"));

        expenseRepository.delete(expense);
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found !"));
    }

}
