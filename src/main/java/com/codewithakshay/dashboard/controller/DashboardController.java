package com.codewithakshay.dashboard.controller;

import com.codewithakshay.dashboard.dto.CategorySummaryResponse;
import com.codewithakshay.dashboard.dto.DashboardSummaryResponse;
import com.codewithakshay.dashboard.service.DashboardService;
import com.codewithakshay.expense.dto.ExpenseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/summary")
    public ResponseEntity<DashboardSummaryResponse> getSummary() {

        return new ResponseEntity<>(dashboardService.getSummary(), HttpStatus.OK);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<ExpenseResponse>> getRecentExpenses() {

        return ResponseEntity.ok().body(dashboardService.getRecentExpenses());
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategorySummaryResponse>> getCategorySummary() {

        return ResponseEntity.status(HttpStatus.OK).body(dashboardService.getCategorySummary());
    }

}
