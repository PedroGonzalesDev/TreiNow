package br.com.treinow.controller;


import br.com.treinow.dtos.WorkoutExerciseDto;
import br.com.treinow.models.entities.WorkoutExerciseEntity;
import br.com.treinow.service.WorkoutExerciseService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/workout/workoutexercise")
public class WorkoutExerciseController {

    @Autowired
    private WorkoutExerciseService workoutExerciseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkoutExerciseEntity createWorkoutExercise(@RequestBody @Valid WorkoutExerciseDto workoutExerciseDto){
        var createdWorkoutExercise = workoutExerciseService.createWorkoutExercise(workoutExerciseDto);
        return createdWorkoutExercise;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<WorkoutExerciseEntity> getAllWorkoutExercise(){
        return workoutExerciseService.getAllWorkoutExercise();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getWorkoutExerciseById(@PathVariable(value = "id")UUID id){
        return workoutExerciseService.findWorkoutExerciseById(id).<ResponseEntity<Object>>map(ResponseEntity::ok).
                orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Workout exercise not found please verify the id"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatedWorkoutExercise(@PathVariable UUID id,
                                                         @RequestBody @Valid WorkoutExerciseDto workoutExerciseDto){
        try{
            var workoutExerciseUpdated = workoutExerciseService.updatedWorkoutExercise(id, workoutExerciseDto);
            return ResponseEntity.status(HttpStatus.OK).body(workoutExerciseUpdated);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Workout exercise not found please verify the id");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteWorkoutExercise(@PathVariable UUID id){
        try{
            workoutExerciseService.deleteWorkoutExercise(id);
            return ResponseEntity.status(HttpStatus.OK).body("Workout exercise deleted successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Workout exercise not found pleas verify the id");
        }
    }


}
