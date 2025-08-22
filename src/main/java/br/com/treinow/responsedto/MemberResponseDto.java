package br.com.treinow.responsedto;

import java.util.UUID;


public record MemberResponseDto(
    UUID id,
    UUID userId,
    Long membershipId
)
{}

