package com.naveens.finora.budget.repository;

import com.naveens.finora.budget.entity.Budget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

    @Query("""
            SELECT COALESCE(SUM(b.amount), 0)
            FROM Budget b
            WHERE b.user.id = :userId
            AND b.month = :month
            AND b.year = :year
            """)
    BigDecimal getTotalBudget(
            @Param("userId") Long userId,
            @Param("month") Integer month,
            @Param("year") Integer year
    );

    Optional<Budget> findByUserIdAndCategoryIdAndMonthAndYear(Long userId, Long categoryId, Integer month, Integer year);

    Page<Budget> findByUserId(Long userId, Pageable pageable);

    Optional<Budget> findByIdAndUserId(Long id, Long userId);

    boolean existsByUserIdAndCategoryIdAndMonthAndYear(Long userId, Long categoryId, Integer month, Integer year);

    boolean existsByUserIdAndCategoryIdAndMonthAndYearAndIdNot(Long userId, Long CategoryId, Integer month, Integer year, Long id);
}
