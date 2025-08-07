package br.com.treinow.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RolePermissionDto(
    @NotNull(message = "Please insert the role to continue")
    UUID roleId,
    @NotNull(message = "Please insert the permissions to continue")
    UUID permissionId
) {
}
