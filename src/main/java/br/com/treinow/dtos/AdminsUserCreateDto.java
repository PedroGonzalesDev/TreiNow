package br.com.treinow.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

//DTO para um admin criar um novo usuário com uma role especfíca
public record AdminsUserCreateDto(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String password,
        @NotBlank @CPF String cpf,
        @NotBlank String phone,
        @NotNull @Valid AddressDto address,
        @NotNull UUID roleId
        ) {

}
