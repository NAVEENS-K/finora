package com.naveens.finora.incomeSource.dto.response;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IncomeSourceResponseDto {

    private Long id;

    private String name;

    private String description;
}
