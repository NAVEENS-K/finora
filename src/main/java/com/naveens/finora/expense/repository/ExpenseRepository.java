package com.naveens.finora.expense.repository;

import com.naveens.finora.expense.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("""
            SELECT COALESCE(SUM(e.amount), 0)
            FROM Expense e
            WHERE e.user.id = :userId
            AND e.expenseDate >= :startDate
            AND e.expenseDate <= :endDate
            """)
    BigDecimal getTotalExpense(
            @Param("userId") Long userId,
            @Param("startDate")LocalDate startDate,
            @Param("endDate") LocalDate endDate
            );

    Page<Expense> findByUserId(Long userId ,Pageable pageable);

    Optional<Expense> findByIdAndUserId(Long id, Long userId);


}
