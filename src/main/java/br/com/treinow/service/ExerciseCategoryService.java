package br.com.treinow.service;

import br.com.treinow.dtos.ExerciseCategoryDto;
import br.com.treinow.models.entities.ExerciseCategoryEntity;
import br.com.treinow.repositories.jpa.ExerciseCategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExerciseCategoryService {

    @Autowired
    private ExerciseCategoryRepository exerciseCategoryRepository;

    public ExerciseCategoryEntity createExerciseCategory(@Valid ExerciseCategoryDto exerciseCategoryDto){
        var exerciseCategoryEntity = new ExerciseCategoryEntity();
        BeanUtils.copyProperties(exerciseCategoryDto, exerciseCategoryEntity);
        return exerciseCategoryRepository.save(exerciseCategoryEntity);
    }

    public List<ExerciseCategoryEntity> getAllExerciseCategories(){
        return exerciseCategoryRepository.findAll();
    }

    public Optional<ExerciseCategoryEntity> findExerciseCategoryById(UUID id){
        return exerciseCategoryRepository.findById(id);
    }

    public ExerciseCategoryEntity updateExerciseCategory(UUID id, @Valid ExerciseCategoryDto exerciseCategoryDto){
        var exerciseCategory = exerciseCategoryRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(exerciseCategoryDto, exerciseCategory, "id");
        return exerciseCategoryRepository.save(exerciseCategory);
    }

    public void deleteExerciseCategory(UUID id) {
        var exerciseCategory = exerciseCategoryRepository.findById(id).orElseThrow();
        exerciseCategoryRepository.delete(exerciseCategory);
    }

}
