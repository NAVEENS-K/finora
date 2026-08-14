package com.naveens.finora.dashboard.controller;

import com.naveens.finora.common.response.ApiResponse;
import com.naveens.finora.dashboard.dto.DashboardResponseDto;
import com.naveens.finora.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<ApiResponse<DashboardResponseDto>> getDashBoard(
            @RequestParam Integer month,
            @RequestParam Integer year
    ){
        DashboardResponseDto dashboard = dashboardService.getDashboard(month, year);

        ApiResponse<DashboardResponseDto> response =
                ApiResponse.<DashboardResponseDto>builder()
                        .success(true)
                        .message("Dashboard retrieved successfully.")
                        .data(dashboard)
                        .build();
        return ResponseEntity.ok(response);
    }
}
