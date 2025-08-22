package br.com.treinow.mapper;

import br.com.treinow.dtos.WorkoutExerciseDto;
import br.com.treinow.models.entities.WorkoutExerciseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkoutExerciseMapper {

    WorkoutExerciseDto toWorkoutExerciseDto(WorkoutExerciseEntity workoutExerciseEntity);
}
