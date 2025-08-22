package br.com.treinow.responsedto;

import java.util.UUID;

public record ExerciseResponseDto(
        UUID id,
        String name,
        UUID exerciseCategoryId,
        UUID muscleGroupId,
        Boolean isPrimary
) {
}
