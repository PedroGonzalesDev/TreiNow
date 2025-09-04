package br.com.treinow.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record MemberDto(
    @NotNull(message = "Payment cannot be null")
    String payment,
    String gender,
    String weight,
    String height,
    String subscriptionStartDate,
    String subscriptionEndDate,
    UserInfoDto userInfoDto,
    AddressInfoDto addressInfoDto,
    UUID userId,
    UUID membershipId
){}
