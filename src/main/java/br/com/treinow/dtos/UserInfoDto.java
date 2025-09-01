package br.com.treinow.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record UserInfoDto(
        @NotBlank(message = "name is mandatory")
        String name,
        @Email(message = "E-mail must be valid")
        @NotBlank(message = "E-mail is mandatory")
        String email,
        @NotBlank(message = "Password is mandatory")
        @Size(min = 6, message = "Password must be at least 6 characters")
        String password,
        @CPF(message = "The identificator number must be valid")
        @NotBlank(message = "The identificator number is mandatory")
        String cpf,
        @NotBlank(message = "Phone number is mandatory")
        String phone
) {
}
