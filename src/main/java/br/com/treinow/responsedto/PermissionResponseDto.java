package br.com.treinow.responsedto;

import java.util.UUID;

public record PermissionResponseDto(
        UUID id,
        String name,
        String description
) {
}
