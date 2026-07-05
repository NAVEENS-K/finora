package com.naveens.finora.category.dto.response;


import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDto {

    private Long id;

    private String name;

    private String description;

}
