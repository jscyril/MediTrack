package com.jscyril.meditrack.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReminderRequest(
        @NotBlank String description,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        @NotNull Long userId,
        @NotNull Long medicineId
) {
}
