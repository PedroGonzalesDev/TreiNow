package br.com.treinow.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SetPasswordDto(
        @NotBlank(message = "Token is mandatory")
        String token,

        @NotBlank(message = "Please insert the new password")
        @Size(min = 6, message = "Password must be at least 6 characters")
        String newPassword
) {
}
