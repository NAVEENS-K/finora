package com.naveens.finora.incomeSource.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateIncomeSourceRequestDto {
    @NotBlank(message = "income source name is required")
    @Size(max = 100, message = "income source name exceeds 100 characters")
    private String name;

    @Size(max = 255, message = "the income source description name exceeds 255 characters")
    private String description;
}
