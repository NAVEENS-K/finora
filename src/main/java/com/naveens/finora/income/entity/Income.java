package com.naveens.finora.income.entity;

import com.naveens.finora.common.entity.BaseEntity;
import com.naveens.finora.incomeSource.entity.IncomeSource;
import com.naveens.finora.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="incomes")
public class Income extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, precision = 15, scale = 2)
    @DecimalMin(value= "0", inclusive = false, message = "amount must be greater than 0")
    private BigDecimal amount;

    @Column(length = 255)
    private String description;

    @Column(nullable = false)
    @NotNull(message = "Recevied date is required")
    private LocalDate receivedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "income_source_id",nullable = false)
    private IncomeSource incomeSource;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
