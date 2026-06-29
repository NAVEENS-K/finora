package com.naveens.finora.auth.dto.request;

import com.naveens.finora.common.enums.Currency;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
    @NotBlank
    private String name;
    @Email
    private String email;
    @Size(min = 8)
    private String password;

    private Currency currency;
}
