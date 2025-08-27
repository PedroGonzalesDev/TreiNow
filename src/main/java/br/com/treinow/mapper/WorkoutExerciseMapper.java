package br.com.treinow.mapper;

import br.com.treinow.dtos.WorkoutExerciseDto;
import br.com.treinow.models.entities.WorkoutExerciseEntity;
import br.com.treinow.responsedto.WorkoutExerciseResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkoutExerciseMapper {

    WorkoutExerciseResponseDto toWorkoutExerciseDto(WorkoutExerciseEntity workoutExerciseEntity);

    List<WorkoutExerciseResponseDto> toWorkoutExerciseResponseDtoList(List<WorkoutExerciseEntity> workoutExerciseEntity);
}
