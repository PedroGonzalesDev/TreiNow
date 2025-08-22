package br.com.treinow.mapper;

import br.com.treinow.dtos.ExerciseDto;
import br.com.treinow.models.entities.ExerciseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {

    ExerciseDto toExerciseDto(ExerciseEntity exerciseEntity);
}
