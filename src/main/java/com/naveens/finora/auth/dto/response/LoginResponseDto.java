package com.naveens.finora.auth.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDto {

    private Long userId;
    private String email;
    private String name;
    private String token;
}
