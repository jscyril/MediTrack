package com.jscyril.meditrack.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalTime;

public record ReminderTimeRequest(
        @Positive int dosage,
        @NotNull LocalTime timeOfDay,
        @NotNull Long reminderId
) {
}
