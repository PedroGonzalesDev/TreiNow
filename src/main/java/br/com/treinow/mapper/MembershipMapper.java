package br.com.treinow.mapper;

import br.com.treinow.dtos.MembershipDto;
import br.com.treinow.models.entities.MembershipEntity;
import br.com.treinow.responsedto.MemberResponseDto;
import br.com.treinow.responsedto.MembershipResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MembershipMapper {

    MembershipResponseDto toMembershipResponseDto(MembershipEntity membershipEntity);

    List<MembershipResponseDto> toMembershipResponseDtoLit(List<MembershipEntity> membershipEntity);
}
