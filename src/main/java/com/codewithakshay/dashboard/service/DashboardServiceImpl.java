package com.codewithakshay.dashboard.service;

import com.codewithakshay.dashboard.dto.CategorySummaryResponse;
import com.codewithakshay.dashboard.dto.DashboardSummaryResponse;
import com.codewithakshay.expense.component.ExpenseMapper;
import com.codewithakshay.expense.dto.ExpenseResponse;
import com.codewithakshay.expense.repository.ExpenseRepository;
import com.codewithakshay.user.entity.User;
import com.codewithakshay.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DashboardServiceImpl implements  DashboardService{

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public DashboardSummaryResponse getSummary() {

        User currentUser = getCurrentUser();

        LocalDateTime currentTime = LocalDateTime.now();

        return DashboardSummaryResponse.builder()
                .totalExpenses(expenseRepository.getTotalExpenses(currentUser))
                .currentMonthExpenses(expenseRepository.getCurrentMonthExpense(currentUser, currentTime.getYear(), currentTime.getMonthValue()))
                .expenseCount(expenseRepository.getTotalExpenseCount(currentUser))
                .build();
    }

    @Override
    public List<ExpenseResponse> getRecentExpenses() {
        User currentUser = getCurrentUser();

        return expenseRepository.findTop10ByUserOrderByExpenseDateDesc(currentUser)
                .stream().map(ExpenseMapper::mapToResponse)
                .toList();
    }

    @Override
    public List<CategorySummaryResponse> getCategorySummary() {
        User currentUser = getCurrentUser();

        return expenseRepository.getCategorySummary(currentUser);
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found !"));
    }
}
