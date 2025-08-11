package br.com.treinow.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(
    @NotBlank(message = "Please insert email to login")
    String email,
    @NotBlank(message = "Please insert password to login")
    String password
) {
}
