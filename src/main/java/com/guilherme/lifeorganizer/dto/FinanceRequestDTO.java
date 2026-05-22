package com.guilherme.lifeorganizer.dto;

import com.guilherme.lifeorganizer.model.enums.FinanceCategory;
import com.guilherme.lifeorganizer.model.enums.FinanceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FinanceRequestDTO(

        @NotBlank(message = "Title")
        String title,

        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be positive")
        BigDecimal amount,

        @NotNull(message = "Type is required")
        FinanceType type,

        @NotNull(message = "Category is required")
        FinanceCategory category,

        LocalDate date,

        String notes

) {
}
