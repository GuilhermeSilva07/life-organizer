package com.guilherme.lifeorganizer.dto;

import com.guilherme.lifeorganizer.model.enums.HabitFrequency;

import java.time.LocalDate;
import java.util.List;

public record HabitDTO(
        Long id,
        String name,
        String description,
        HabitFrequency Frequency,
        List<LocalDate> completions
) {
}
