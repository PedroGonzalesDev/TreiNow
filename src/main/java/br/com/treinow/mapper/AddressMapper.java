package br.com.treinow.mapper;

import br.com.treinow.dtos.AddressDto;
import br.com.treinow.models.entities.AddressEntity;
import br.com.treinow.responsedto.AddressResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressResponseDto toAddressResponseDto(AddressEntity addressEntity);

    List<AddressResponseDto> toAddressResponseDtoList(List<AddressEntity> addressEntity);
}
