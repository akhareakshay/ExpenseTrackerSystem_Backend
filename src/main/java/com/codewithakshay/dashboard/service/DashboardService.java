package com.codewithakshay.dashboard.service;

import com.codewithakshay.dashboard.dto.CategorySummaryResponse;
import com.codewithakshay.dashboard.dto.DashboardSummaryResponse;
import com.codewithakshay.expense.dto.ExpenseResponse;

import java.util.List;

public interface DashboardService {

    DashboardSummaryResponse getSummary();

    List<ExpenseResponse> getRecentExpenses();

    List<CategorySummaryResponse> getCategorySummary();

}
