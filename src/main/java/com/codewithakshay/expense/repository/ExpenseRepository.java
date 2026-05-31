package com.codewithakshay.expense.repository;

import com.codewithakshay.dashboard.dto.CategorySummaryResponse;
import com.codewithakshay.expense.entity.Expense;
import com.codewithakshay.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUser(User user);

    Optional<Expense> findByIdAndUser(Long id, User user);

    @Query("""
            SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE e.user = :user
           """)
    BigDecimal getTotalExpenses(User user);

    @Query("""
           SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE e.user = :user
                      AND EXTRACT(YEAR FROM e.expenseDate) = :year 
                      AND EXTRACT(MONTH FROM e.expenseDate) = :month
           """)
    BigDecimal getCurrentMonthExpense(User user, int year, int month);

    @Query("""
           SELECT COUNT(e) FROM Expense e WHERE e.user = :user
           """)
    Long getTotalExpenseCount(User user);

    List<Expense> findTop10ByUserOrderByExpenseDateDesc(User user);

    @Query("""
           SELECT new com.codewithakshay.dashboard.dto.CategorySummaryResponse(e.category, SUM(e.amount)) FROM Expense e  
                      where e.user = :user GROUP BY e.category
           """)
    List<CategorySummaryResponse> getCategorySummary(User user);
}
