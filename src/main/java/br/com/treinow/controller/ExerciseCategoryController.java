package br.com.treinow.controller;


import br.com.treinow.dtos.ExerciseCategoryDto;
import br.com.treinow.dtos.ExerciseDto;
import br.com.treinow.models.entities.ExerciseCategoryEntity;
import br.com.treinow.models.entities.ExerciseEntity;
import br.com.treinow.service.ExerciseCategoryService;
import br.com.treinow.service.ExerciseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/workout/exercisecategory")
public class ExerciseCategoryController {

    @Autowired
    private ExerciseCategoryService exerciseCategoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ATRIBUTO_CREATE')")
    public ExerciseCategoryEntity createExerciseCategory(@RequestBody @Valid ExerciseCategoryDto exerciseCategoryDto){
        var createdExerciseCategory = exerciseCategoryService.createExerciseCategory(exerciseCategoryDto);
        return createdExerciseCategory;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ATRIBUTO_READ')")
    public List<ExerciseCategoryEntity> getAllExerciseCategories(){
        return exerciseCategoryService.getAllExerciseCategories();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ATRIBUTO_READ')")
    public ResponseEntity<Object> getExerciseCategoryById (@PathVariable(value = "id")UUID id) {
        try {
            var exerciseCategory = exerciseCategoryService.findExerciseCategoryById(id);
            return ResponseEntity.status(HttpStatus.OK).body(exerciseCategory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exercise not found please verify the id");
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ATRIBUTO_UPDATE')")
    public ResponseEntity<Object> updateExerciseCategory(@PathVariable UUID id,
                                                  @RequestBody @Valid ExerciseCategoryDto exerciseCategoryDto){
        try{
            var updatedExerciseCategory = exerciseCategoryService.updateExerciseCategory(id, exerciseCategoryDto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedExerciseCategory);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exercise Category not found please verify the id");
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ATRIBUTO_DELETE')")
    public ResponseEntity<Object> deleteExercise(@PathVariable UUID id){
        try{
            exerciseCategoryService.deleteExerciseCategory(id);
            return ResponseEntity.status(HttpStatus.OK).body("Exercise Category deleted successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exercise Category not found please verify the id");
        }
    }

}
