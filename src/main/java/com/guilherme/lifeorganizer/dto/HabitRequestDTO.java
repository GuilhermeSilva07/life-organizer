package com.guilherme.lifeorganizer.dto;

import com.guilherme.lifeorganizer.model.enums.HabitFrequency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HabitRequestDTO(

        @NotBlank(message = "Name is required")
        String name,

        String description,

        @NotNull(message = "Frequency id required")
        HabitFrequency Frequency
) {
}
