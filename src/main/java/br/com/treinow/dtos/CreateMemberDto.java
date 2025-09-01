package br.com.treinow.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateMemberDto(
        @NotNull(message = "User data cannot be null")
        UserInfoDto userInfoDto,
        @NotNull(message = "Address cannot be null")
        AddressInfoDto addressInfoDto,
        @NotNull(message = "membership id is mandatory")
        UUID membershipId
        ) {
}
