package com.jscyril.meditrack.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public record StockRequest(
        @PositiveOrZero int quantity,
        @NotNull LocalDate expiryDate,
        @NotNull Long userId,
        @NotNull Long medicineId
) {
}
