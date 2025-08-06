package br.com.treinow.service;

import br.com.treinow.dtos.WorkoutExerciseDto;
import br.com.treinow.models.entities.WorkoutExerciseEntity;
import br.com.treinow.repositories.jpa.WorkouExerciseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorkoutExerciseService {

    @Autowired
    private WorkouExerciseRepository workoutExerciseRepository;

    public WorkoutExerciseEntity createWorkoutExercise(@Valid WorkoutExerciseDto workoutExerciseDto){
        var workoutExerciseEntity = new WorkoutExerciseEntity();
        BeanUtils.copyProperties(workoutExerciseDto, workoutExerciseEntity);
        return workoutExerciseRepository.save(workoutExerciseEntity);
    }

    public List<WorkoutExerciseEntity> getAllWorkoutExercise(){
        return workoutExerciseRepository.findAll();
    }

    public Optional<WorkoutExerciseEntity> findWorkoutExerciseById(UUID id){
        return workoutExerciseRepository.findById(id);
    }

    public WorkoutExerciseEntity updatedWorkoutExercise(UUID id, @Valid WorkoutExerciseDto workoutExerciseDto){
        var workoutExercise = workoutExerciseRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(workoutExerciseDto, workoutExercise, "id");
        return workoutExerciseRepository.save(workoutExercise);
    }

    public void deleteWorkoutExercise(UUID id){
        var workoutExercise = workoutExerciseRepository.findById(id).orElseThrow();
        workoutExerciseRepository.delete(workoutExercise);
    }
}
