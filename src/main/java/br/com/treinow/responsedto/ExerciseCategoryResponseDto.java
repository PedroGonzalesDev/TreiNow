package br.com.treinow.responsedto;

import java.util.UUID;

public record ExerciseCategoryResponseDto(
        UUID id,
        String name,
        String description
) {
}
