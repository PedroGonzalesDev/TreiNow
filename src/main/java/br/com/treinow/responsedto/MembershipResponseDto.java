package br.com.treinow.responsedto;

import java.util.UUID;

public record MembershipResponseDto(
        UUID id,
        String name,
        String description,
        String durationType,
        Long duration
) {}
