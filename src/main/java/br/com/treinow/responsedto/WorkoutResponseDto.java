package br.com.treinow.responsedto;

import java.util.UUID;

public record WorkoutResponseDto(
        UUID id,
        String name,
        UUID workoutExerciseId,
        String observation,
        String createdBy,
        String createdAt,
        String lastUpdateAt
) {
}
