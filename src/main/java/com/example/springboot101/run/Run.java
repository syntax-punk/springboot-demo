package com.example.springboot101.run;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record Run(
        Integer id,
        @NotEmpty
        String title,
        @PastOrPresent
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Positive
        Integer miles,
        Location location

) {
        @AssertTrue(message = "Completed date must be after started date")
        public boolean isCompletedOnComesAfterStartedOn() {
                return completedOn == null || startedOn == null || !completedOn.isBefore(startedOn);
        }
}
