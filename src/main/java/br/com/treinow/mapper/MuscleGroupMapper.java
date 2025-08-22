package br.com.treinow.mapper;

import br.com.treinow.dtos.MuscleGroupDto;
import br.com.treinow.models.entities.MuscleGroupEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MuscleGroupMapper {

    MuscleGroupDto toMuscleGroupDto(MuscleGroupEntity muscleGroupEntity);
}
