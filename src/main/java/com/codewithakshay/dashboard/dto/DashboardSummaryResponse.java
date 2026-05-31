package com.codewithakshay.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class DashboardSummaryResponse {

    private BigDecimal totalExpenses;
    private BigDecimal currentMonthExpenses;
    private Long expenseCount;

}
