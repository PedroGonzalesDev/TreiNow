package br.com.treinow.dtos;


import br.com.treinow.models.entities.AddressEntity;
import br.com.treinow.models.entities.RoleEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

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
    @NotNull(message = "Select the address is required to continue")
    AddressDto address,
//    @NotNull(message = "Select the role is required to continue")
    UUID roleId,
    @JsonProperty(defaultValue = "true")
    Boolean isActive,
    String createdAt,
    String lastLogin
) {}
