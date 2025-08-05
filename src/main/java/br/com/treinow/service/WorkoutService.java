package br.com.treinow.service;

import br.com.treinow.dtos.WorkoutDto;
import br.com.treinow.models.entities.WorkoutEntity;
import br.com.treinow.repositories.jpa.WorkoutRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    public WorkoutEntity createWorkout(@Valid WorkoutDto workoutDto){
        var workoutEntity = new WorkoutEntity();
        BeanUtils.copyProperties(workoutDto, workoutEntity);
        return workoutRepository.save(workoutEntity);
    }

    public List<WorkoutEntity> getAllWorkouts(){
        return workoutRepository.findAll();
    }

    public Optional<WorkoutEntity> findById(UUID id){
        return workoutRepository.findById(id);
    }

    public WorkoutEntity updateWorkout(UUID id, @Valid WorkoutDto workoutDto){
        var workout = workoutRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(workoutDto, workout, "id");
        return workoutRepository.save(workout);
    }

    public void deleteWorkout (UUID id){
        var workout = workoutRepository.findById(id).orElseThrow();
        workoutRepository.delete(workout);
    }

}
