package com.jscyril.meditrack.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record HealthLogRequest(
        @NotNull LocalDateTime logTime,
        @NotBlank String logType,
        @NotBlank String value
) {
}
