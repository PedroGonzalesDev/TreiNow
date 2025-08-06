package br.com.treinow.dtos;

import jakarta.validation.constraints.NotBlank;

public record ExerciseCategoryDto(
    @NotBlank(message = "Insert name to continue")
    String name,
    String description
) {
}
