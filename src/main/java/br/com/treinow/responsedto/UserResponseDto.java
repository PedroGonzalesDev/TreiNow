package br.com.treinow.responsedto;

import br.com.treinow.dtos.AddressDto;
import br.com.treinow.dtos.RoleDto;

import java.util.UUID;


public record UserResponseDto (
        UUID id,
        String name,
        String email,
        String cpf,
        String phone,
        AddressDto address,
        RoleDto roleDto,
        Boolean isActive,
        String createdAt,
        String updatedAt,
        String lastLogin

){}


