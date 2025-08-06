package br.com.treinow.service;

import br.com.treinow.dtos.ExerciseDto;
import br.com.treinow.models.entities.ExerciseEntity;
import br.com.treinow.repositories.jpa.ExerciseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    public ExerciseEntity createExercise(@Valid ExerciseDto exerciseDto){
        var exerciseEntity = new ExerciseEntity();
        BeanUtils.copyProperties(exerciseDto, exerciseEntity);
        return exerciseRepository.save(exerciseEntity);
    }

    public List<ExerciseEntity> getAllExercise(){
        return exerciseRepository.findAll();
    }

    public Optional<ExerciseEntity> findExerciseById(UUID id){
        return exerciseRepository.findById(id);
    }

    public ExerciseEntity updateExercise(UUID id, @Valid ExerciseDto exerciseDto){
        var exercise = exerciseRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(exerciseDto, exercise, "id");
        return exerciseRepository.save(exercise);
    }

    public void deleteExercise(UUID id){
        var exercise = exerciseRepository.findById(id).orElseThrow();
        exerciseRepository.delete(exercise);
    }
}
