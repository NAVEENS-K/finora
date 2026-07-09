package com.naveens.finora.incomeSource.mapper;

import com.naveens.finora.incomeSource.dto.request.CreateIncomeSourceRequestDto;
import com.naveens.finora.incomeSource.dto.response.IncomeSourceResponseDto;
import com.naveens.finora.incomeSource.entity.IncomeSource;
import org.springframework.stereotype.Component;

@Component
public class IncomeSourceMapper {

    public IncomeSource toEntity(CreateIncomeSourceRequestDto request){
        IncomeSource incomeSource = new IncomeSource();

        incomeSource.setName(request.getName());
        incomeSource.setDescription(request.getDescription());

        return incomeSource;
    }

    public IncomeSourceResponseDto toResponse(IncomeSource incomeSource){
        return IncomeSourceResponseDto.builder()
                .id(incomeSource.getId())
                .name(incomeSource.getName())
                .description(incomeSource.getDescription())
                .build();

    }

}
