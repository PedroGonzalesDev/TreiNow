package br.com.treinow.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserRoleUpdateDto(
        @NotNull UUID newRoleId
) {
}
