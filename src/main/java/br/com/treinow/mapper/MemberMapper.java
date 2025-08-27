package br.com.treinow.mapper;


import br.com.treinow.dtos.MemberDto;
import br.com.treinow.models.entities.MemberEntity;
import br.com.treinow.responsedto.MemberResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberResponseDto toMemberResponseDto(MemberEntity memberEntity);

    List<MemberResponseDto> toMemberResponseDtoList(List<MemberEntity> memberEntity);
}
