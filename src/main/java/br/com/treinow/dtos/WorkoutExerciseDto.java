package br.com.treinow.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record WorkoutExerciseDto(
    @NotNull(message = "Please select the exercise to continue")
    UUID exerciseId,
    Long sets,
    Long repetitions,
    Long weight,
    String observation
) {
}
