package br.com.treinow.mapper;

import br.com.treinow.dtos.ExerciseCategoryDto;
import br.com.treinow.models.entities.ExerciseCategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExerciseCategoryMapper {

    ExerciseCategoryDto toExerciseCategoryDto(ExerciseCategoryEntity exerciseCategoryEntity);
}
