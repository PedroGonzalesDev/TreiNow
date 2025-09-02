package br.com.treinow.responsedto;

import java.util.UUID;


public record MemberResponseDto(
    UUID id,
    UUID userId,
    String name,
    UUID membershipId,
    String payment,
    String subscriptionStartDate,
    String subscriptionEndDate
)
{}

