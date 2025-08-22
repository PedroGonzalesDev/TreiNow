package br.com.treinow.mapper;


import br.com.treinow.responsedto.UserResponseDto;
import br.com.treinow.models.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {

    @Mapping(source = "role", target = "roleDto")
    UserResponseDto toUserResponseDto(UserEntity userEntity);

    List<UserResponseDto> toUserResponseDtoList(List<UserEntity> userList);
}
