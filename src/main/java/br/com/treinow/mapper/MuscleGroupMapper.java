package br.com.treinow.mapper;

import br.com.treinow.dtos.MuscleGroupDto;
import br.com.treinow.models.entities.MuscleGroupEntity;
import br.com.treinow.responsedto.MuscleGroupResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MuscleGroupMapper {

    MuscleGroupResponseDto toMuscleGroupResponseDto(MuscleGroupEntity muscleGroupEntity);

    List<MuscleGroupResponseDto> toMuscleGroupResponseDtoList(List<MuscleGroupEntity> muscleGroupEntity);
}
