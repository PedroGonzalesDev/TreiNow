package br.com.treinow.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDto(
    @NotBlank
    String name,
    @NotBlank
    String email,
    @NotBlank
    String password,
    @NotNull
    Long phone,
    Long identityNumber,
    Long addressId,
    @NotNull
    Long roleId,
    @NotNull
    Boolean isActive,
    String createdAt,
    String lastLogin
) {}
