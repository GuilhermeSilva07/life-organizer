package com.guilherme.lifeorganizer.dto;

import jakarta.validation.constraints.NotBlank;

public record AiRequestDTO(
        @NotBlank(message = "Question is required")
        String question
) {}