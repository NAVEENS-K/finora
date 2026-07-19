package com.naveens.finora.income.dto.response;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IncomeResponseDto {

    private Long id;

    private BigDecimal amount;

    private String description;

    private LocalDate receivedDate;

    private Long incomeSourceId;

    private String incomeSourceName;
}
