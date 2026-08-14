package com.naveens.finora.income.repository;

import com.naveens.finora.income.entity.Income;
import com.naveens.finora.incomeSource.entity.IncomeSource;
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
public interface IncomeRepository extends JpaRepository<Income,Long> {

    @Query("""
            SELECT COALESCE(SUM(i.amount), 0)
            FROM Income i
            WHERE i.user.id = :userId
            AND i.receivedDate >= :startDate
            AND i.receivedDate <= :endDate
            """)
    BigDecimal getTotalIncome(
            @Param("userId") Long userId,
            @Param("startDate")LocalDate startDate,
            @Param("endDate") LocalDate endDate
            );

Page<Income> findByUserId(Long userId, Pageable pageable);
Optional<Income> findByIdAndUserId(Long id, Long userId);
}
