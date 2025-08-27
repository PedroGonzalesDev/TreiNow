package br.com.treinow.mapper;

import br.com.treinow.dtos.WorkoutDto;
import br.com.treinow.models.entities.WorkoutEntity;
import br.com.treinow.responsedto.WorkoutResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {

    WorkoutResponseDto toWorkoutResponseDto(WorkoutEntity workoutEntity);

    List<WorkoutResponseDto> toWorkoutResponseDtoList(List<WorkoutEntity> workoutEntity);
}
