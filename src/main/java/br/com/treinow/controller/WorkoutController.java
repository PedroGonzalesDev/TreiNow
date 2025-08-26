package br.com.treinow.controller;

import br.com.treinow.dtos.WorkoutDto;
import br.com.treinow.models.entities.WorkoutEntity;
import br.com.treinow.service.WorkoutService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/workout")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('TREINO_CREATE')")
    public WorkoutEntity createWorkout(@RequestBody @Valid WorkoutDto workoutDto){
        var createdWorkout = workoutService.createWorkout(workoutDto);
        return createdWorkout;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('TREINO_READ')")
    public List<WorkoutEntity> getAllWorkouts(){
        return workoutService.getAllWorkouts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('TREINO_READ')")
    public ResponseEntity<Object> getWorkoutById(@PathVariable(value = "id")UUID id){
        return workoutService.findById(id).<ResponseEntity<Object>>map(ResponseEntity::ok).
                orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Workout not found please verify the id"));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('TREINO_UPDATE')")
    public ResponseEntity<Object> updateWorkout(@PathVariable UUID id,
                                                @RequestBody @Valid WorkoutDto workoutDto){
        try{
            var workoutUpdated = workoutService.updateWorkout(id, workoutDto);
            return ResponseEntity.status(HttpStatus.OK).body(workoutUpdated);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Workout not found please verify the id");
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('TREINO_DELETE')")
    public ResponseEntity<Object> deleteWorkout(@PathVariable UUID id){
        try{
            workoutService.deleteWorkout(id);
            return ResponseEntity.status(HttpStatus.OK).body("Workout deleted sucessfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Workout not found please verify the id");
        }
    }

}
