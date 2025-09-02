package br.com.treinow.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record MemberDto(
    @NotNull(message = "Payment cannot be null")
    String payment,
    @NotNull(message = "Gender cannot be null")
    String gender,
    String weight,
    String height,
    @NotNull(message = "Must contain the subscription start date")
    String subscriptionStartDate,
    @NotNull(message = "Must contain the subscription end date")
    String subscriptionEndDate
){}
