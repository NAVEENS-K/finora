package com.naveens.finora.budget.dto.response;


import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BudgetResponseDto {

    private Long id;

    private BigDecimal amount;

    private Integer month;

    private Integer year;

    private Long categoryId;

    private String categoryName;

}
