package br.com.treinow.responsedto;

import br.com.treinow.dtos.RoleDto;

import java.util.UUID;


public record UserResponseDto (
    UUID id,
    String name,
    String email,
    String phone,
    RoleDto roleDto

){}


