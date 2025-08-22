package br.com.treinow.responsedto;

import java.util.UUID;

public record RoleResponseDto(
        UUID id,
        String name,
        String description
) {
}
