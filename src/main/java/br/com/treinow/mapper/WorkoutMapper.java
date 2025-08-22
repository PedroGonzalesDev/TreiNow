package br.com.treinow.mapper;

import br.com.treinow.dtos.WorkoutDto;
import br.com.treinow.models.entities.WorkoutEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {

    WorkoutDto toWorkoutDto(WorkoutEntity workoutEntity);
}
