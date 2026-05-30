package com.codewithakshay.expense.repository;

import com.codewithakshay.expense.entity.Expense;
import com.codewithakshay.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUser(User user);

    Optional<Expense> findByIdAndUser(Long id, User user);
}
