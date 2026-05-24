package com.guilherme.lifeorganizer.dto;

import com.guilherme.lifeorganizer.model.enums.TaskPriority;
import com.guilherme.lifeorganizer.model.enums.TaskStatus;

import java.time.LocalDate;

public record TaskDTO(
        Long id,
        String title,
        String description,
        TaskPriority priority,
        TaskStatus status,
        LocalDate dueDate
) {
}
