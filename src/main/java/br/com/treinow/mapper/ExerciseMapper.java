package br.com.treinow.mapper;

import br.com.treinow.dtos.ExerciseDto;
import br.com.treinow.models.entities.ExerciseEntity;
import br.com.treinow.responsedto.ExerciseResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {

    ExerciseResponseDto toExerciseResponseDto(ExerciseEntity exerciseEntity);

    List<ExerciseResponseDto> toExerciseResponseDtoList(List<ExerciseEntity> exerciseEntity);
}
