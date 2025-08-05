package br.com.treinow.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressDto(
        @NotBlank(message = "Street is required to continue")
        String street,
        @NotNull(message = "Number of the house is required")
        Long number,
        @NotBlank(message = "Neighborhood is required to continue")
        String neighborhood,
        String complement,
        @NotBlank(message = "City is required to continue")
        String city,
        @NotBlank(message = "State is required to continue")
        String state
        ) {
}
