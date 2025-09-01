package br.com.treinow.dtos;

import jakarta.validation.constraints.NotBlank;

public record AddressInfoDto(
        @NotBlank(message = "Street is mandatory")
        String street,
        @NotBlank(message = "House number is mandatory")
        String number,
        String complement,
        @NotBlank(message = "City is mandatory")
        String city,
        @NotBlank(message = "State is mandatory")
        String state,
        @NotBlank(message = "zip code is mandatory")
        String zipCode
) {
}
