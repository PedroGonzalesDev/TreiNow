package br.com.treinow.mapper;


import br.com.treinow.models.entities.MemberEntity;
import br.com.treinow.responsedto.MemberResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    @Mapping(source = "userEntity.id", target = "userId")
    @Mapping(source = "userEntity.name", target = "name")
    @Mapping(source = "membershipEntity.id", target = "membershipId")
    MemberResponseDto toMemberResponseDto(MemberEntity memberEntity);

    List<MemberResponseDto> toMemberResponseDtoList(List<MemberEntity> memberEntity);
}
