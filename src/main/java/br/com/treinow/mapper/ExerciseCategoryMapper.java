package br.com.treinow.mapper;

import br.com.treinow.dtos.ExerciseCategoryDto;
import br.com.treinow.models.entities.ExerciseCategoryEntity;
import br.com.treinow.responsedto.ExerciseCategoryResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExerciseCategoryMapper {

    ExerciseCategoryResponseDto toExerciseCategoryResponseDto(ExerciseCategoryEntity exerciseCategoryEntity);

    List<ExerciseCategoryResponseDto> toExerciseCategoryResponseDtoList(List<ExerciseCategoryEntity> exerciseCategoryEntity);
}
