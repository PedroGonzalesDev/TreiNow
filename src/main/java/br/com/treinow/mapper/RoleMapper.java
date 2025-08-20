package br.com.treinow.mapper;


import br.com.treinow.dtos.RoleDto;
import br.com.treinow.models.entities.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") //Transforma mapper em bean do Spring
public interface RoleMapper {

    RoleDto toRoleDto(RoleEntity roleEntity);
}
