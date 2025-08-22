package br.com.treinow.mapper;

import br.com.treinow.dtos.AddressDto;
import br.com.treinow.models.entities.AddressEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDto toAddressDto(AddressEntity addressEntity);
}
