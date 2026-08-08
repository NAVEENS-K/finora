package com.naveens.finora.budget.mapper;

import com.naveens.finora.budget.dto.request.CreateBudgetRequestDto;
import com.naveens.finora.budget.dto.response.BudgetResponseDto;
import com.naveens.finora.budget.entity.Budget;
import org.springframework.stereotype.Component;

@Component
public class BudgetMapper {
    public Budget toEntity(CreateBudgetRequestDto request){
        Budget budget = new Budget();

        budget.setAmount(request.getAmount());
        budget.setMonth(request.getMonth());
        budget.setYear(request.getYear());

        return budget;
    }

    public BudgetResponseDto toResponse(Budget budget){
        return BudgetResponseDto.builder()
                .id(budget.getId())
                .amount(budget.getAmount())
                .month(budget.getMonth())
                .year(budget.getYear())
                .categoryId(budget.getCategory().getId())
                .categoryName(budget.getCategory().getName())
                .build();
    }

}
