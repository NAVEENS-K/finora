package com.naveens.finora.category.dto.request;

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
public class CreateCategoryRequestDto {
    @NotBlank(message = "Category name is required.")
    @Size(max = 100, message= "Category name not more then 100 words.")
    private String name;

    @Size(max = 100, message = "Category description is not more than 100 words.")
    private String Description;

}
