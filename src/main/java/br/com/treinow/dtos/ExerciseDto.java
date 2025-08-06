package br.com.treinow.dtos;

import java.util.UUID;

public record ExerciseDto(
    String name,
    UUID exerciseCategoryId,
    UUID muscleGroupId,
    Boolean isPrimary
) {
}
