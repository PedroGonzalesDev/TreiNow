package br.com.treinow.dtos;

import jakarta.validation.constraints.NotBlank;

public record MuscleGroupDto(
    @NotBlank(message = "Insert name to continue")
    String name
) {
}
