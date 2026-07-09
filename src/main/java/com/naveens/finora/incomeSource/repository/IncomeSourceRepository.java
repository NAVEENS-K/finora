package com.naveens.finora.incomeSource.repository;

import com.naveens.finora.incomeSource.entity.IncomeSource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeSourceRepository extends JpaRepository<IncomeSource, Long> {
    boolean existsByUserIdAndName(Long id, String name);
}
