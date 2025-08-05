package br.com.treinow.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record WorkoutDto(

    @NotBlank(message = "Please insert name to continue")
    String name,
    @NotNull(message = "Please select at least one exercise to continue")
    UUID workoutExerciseId,
    String observation,
    String createdBy,
    String createdAt,
    String lastUpdateAt

) {}
