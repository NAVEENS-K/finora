package com.naveens.finora.income.mapper;

import com.naveens.finora.income.dto.request.CreateIncomeRequestDto;
import com.naveens.finora.income.dto.response.IncomeResponseDto;
import com.naveens.finora.income.entity.Income;
import org.springframework.stereotype.Component;

@Component
public class IncomeMapper {

    public Income toEntity(CreateIncomeRequestDto request){
        Income income = new Income();

        income.setAmount(request.getAmount());
        income.setDescription(request.getDescription());
        income.setReceivedDate(request.getReceivedDate());

        return income;
    }

    public IncomeResponseDto toResponse(Income income){

        return IncomeResponseDto.builder()
                .id(income.getId())
                .amount(income.getAmount())
                .description(income.getDescription())
                .receivedDate(income.getReceivedDate())
                .incomeSourceId(income.getIncomeSource().getId())
                .incomeSourceName(income.getIncomeSource().getName())
                .build();

    }
}
