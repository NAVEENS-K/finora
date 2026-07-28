package com.naveens.finora.expense.entity;

import com.naveens.finora.category.entity.Category;
import com.naveens.finora.common.entity.BaseEntity;
import com.naveens.finora.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Expense extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, precision = 15, scale = 2)
    @DecimalMin(value = "0", inclusive = false, message = "amount must be greater than 0.")
    private BigDecimal amount;

    @Column(length = 255)
    private String description;

    @Column(nullable = false)
    @NotNull(message = "Expense date must required")
    private LocalDate expenseDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
