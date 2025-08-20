package br.com.treinow.dtos;

import java.util.UUID;

public record RoleDto(
        UUID id,
        String name
) {
}
