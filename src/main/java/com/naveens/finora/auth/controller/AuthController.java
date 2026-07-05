package com.naveens.finora.auth.controller;

import com.naveens.finora.auth.dto.request.RegisterRequestDto;
import com.naveens.finora.auth.dto.response.UserResponseDto;
import com.naveens.finora.auth.service.AuthService;
import com.naveens.finora.common.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDto>> register(
            @Valid
            @RequestBody RegisterRequestDto request)
    {
            UserResponseDto registeredUser = authService.register(request);

            ApiResponse<UserResponseDto> response =
                    ApiResponse.<UserResponseDto>builder()
                            .success(true)
                            .message("User registered Successfully.")
                            .data(registeredUser)
                            .build();


                    return ResponseEntity
                            .status(HttpStatus.CREATED)
                            .body(response);
    }
}
