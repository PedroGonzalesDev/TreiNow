package br.com.treinow.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record MembershipDto(
        @NotBlank(message = "Name cannot be blank")
        String name,
        @NotBlank(message = "Duration type is required")
        String durationType,
        @NotNull(message = "Insert the duration to continue")
        Long duration,
        @NotNull(message = "Price is required")
        Double price
) {
}
