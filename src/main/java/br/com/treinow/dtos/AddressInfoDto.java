package br.com.treinow.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddressInfoDto(
        @NotBlank(message = "Street is required")
        String street,
        @NotNull(message = "House number is required")
        Long number,
        String complement,
        @NotBlank(message = "City is required")
        String city,
        @NotBlank(message = "State is required")
        String state,
        @NotBlank(message = "zip code is required")
        @Size(min = 8, max = 8)
        String zipCode
) {
}
