package com.guilherme.lifeorganizer.dto;

import com.guilherme.lifeorganizer.model.enums.FinanceCategory;
import com.guilherme.lifeorganizer.model.enums.FinanceType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FinanceDTO(
        Long id,
        String title,
        BigDecimal amount,
        FinanceType type,
        FinanceCategory category,
        LocalDate date,
        String notes
) {
}
