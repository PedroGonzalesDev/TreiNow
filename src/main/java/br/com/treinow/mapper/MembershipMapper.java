package br.com.treinow.mapper;

import br.com.treinow.dtos.MembershipDto;
import br.com.treinow.models.entities.MembershipEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MembershipMapper {

    MembershipDto toMembershipDto(MembershipEntity membershipEntity);
}
