package br.com.treinow.dtos;

import java.util.UUID;


public record UserResponseDto (
    UUID id,
    String name,
    String email,
    String phone,
    RoleDto roleDto

){}


