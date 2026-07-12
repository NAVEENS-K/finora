package com.naveens.finora.incomeSource.repository;

import com.naveens.finora.incomeSource.entity.IncomeSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeSourceRepository extends JpaRepository<IncomeSource, Long> {
    boolean existsByUserIdAndName(Long id, String name);
    List<IncomeSource> findByUserIdOrderByNameAsc(Long userId);
    boolean existsByUserIdAndNameAndIdNot(Long UserId, String name, Long id);
}
