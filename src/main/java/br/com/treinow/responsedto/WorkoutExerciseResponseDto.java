package br.com.treinow.responsedto;

import java.util.UUID;

public record WorkoutExerciseResponseDto(
        UUID exerciseId,
        Long sets,
        Long repetitions,
        Long weight,
        String observation
) {
}
