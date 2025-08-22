package br.com.treinow.mapper;

import br.com.treinow.dtos.PermissionDto;
import br.com.treinow.models.entities.PermissionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    PermissionDto toPermissionDto(PermissionEntity permissionEntity);
}
