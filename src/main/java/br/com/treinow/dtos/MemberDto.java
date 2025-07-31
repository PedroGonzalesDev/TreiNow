package br.com.treinow.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record MemberDto(
    UUID userId,
    @NotNull(message = "Membership cannot be null")
    Long membershipId,
    @NotNull(message = "Payment cannot be null")
    String payment,
    @NotNull(message = "Gender cannot be null")
    String gender,
    String state,
    String weight,
    String height,
    @NotNull(message = "Must contain the subscription start date")
    String subscriptionStartDate,
    @NotNull(message = "Must contain the subscription end date")
    String subscriptionEndDate
){}
