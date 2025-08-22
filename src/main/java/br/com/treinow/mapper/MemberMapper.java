package br.com.treinow.mapper;


import br.com.treinow.dtos.MemberDto;
import br.com.treinow.models.entities.MemberEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberDto toMemberDto(MemberEntity memberEntity);
}
