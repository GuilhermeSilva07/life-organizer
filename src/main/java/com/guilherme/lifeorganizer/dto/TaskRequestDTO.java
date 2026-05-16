package com.guilherme.lifeorganizer.dto;

import com.guilherme.lifeorganizer.model.enums.TaskPriority;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record TaskRequestDTO(

        @NotBlank(message = "Title is required")
        String title,
        String description,
        TaskPriority priority,
        LocalDate duedate
) {
}
