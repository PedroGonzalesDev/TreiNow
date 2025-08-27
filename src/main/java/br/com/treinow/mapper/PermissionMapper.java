package br.com.treinow.mapper;

import br.com.treinow.dtos.PermissionDto;
import br.com.treinow.models.entities.PermissionEntity;
import br.com.treinow.responsedto.PermissionResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    PermissionResponseDto toPermissionResponseDto(PermissionEntity permissionEntity);

    List<PermissionResponseDto> toPermissionResponseDtoList(List<PermissionEntity> permissionEntity);
}
