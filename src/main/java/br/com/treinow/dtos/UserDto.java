package br.com.treinow.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

public record UserDto(

    @NotBlank(message = "Name cannot be blank")
    String name,
    @NotBlank(message = "Email is required")
    String email,
    @NotBlank(message = "Password is required")
    String password,
    @NotNull(message = "CPF cannot be null")
    @CPF(message = "CPF is invalid")
    String cpf,
    @NotBlank(message = "Phone is required")
    String phone,
    Long addressId,
    @NotNull(message = "Select the role is required to continue")
    Long roleId,
    @JsonProperty(defaultValue = "true")
    Boolean isActive,
    String createdAt,
    String lastLogin
) {}
