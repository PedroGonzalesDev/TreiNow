package br.com.treinow.mapper;


import br.com.treinow.dtos.RoleDto;
import br.com.treinow.models.entities.RoleEntity;
import br.com.treinow.responsedto.RoleResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring") //Transforma mapper em bean do Spring
public interface RoleMapper {

    RoleResponseDto toRoleResponseDto(RoleEntity roleEntity);

    List<RoleResponseDto> toRoleReponseDtoList(List<RoleEntity> roleList);
}
