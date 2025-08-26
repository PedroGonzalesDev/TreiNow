package br.com.treinow.controller;


import br.com.treinow.dtos.ExerciseDto;
import br.com.treinow.models.entities.ExerciseEntity;
import br.com.treinow.service.ExerciseService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/workout/exercise")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('EXERCICIO_CREATE')")
    public ExerciseEntity createExercise(@RequestBody @Valid ExerciseDto exerciseDto){
        var createdExercise = exerciseService.createExercise(exerciseDto);
        return createdExercise;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('EXERCICIO_READ')")
    public List<ExerciseEntity> getAllExercise(){
        return exerciseService.getAllExercise();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('EXERCICIO_READ')")
    public ResponseEntity<Object> getExerciseById (@PathVariable(value = "id")UUID id) {
        try {
            var exercise = exerciseService.findExerciseById(id);
            return ResponseEntity.status(HttpStatus.OK).body(exercise);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exercise not found please verify the id");
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EXERCICIO_UPDATE')")
    public ResponseEntity<Object> updatedExercise(@PathVariable UUID id,
                                                  @RequestBody @Valid ExerciseDto exerciseDto){
        try{
            var exerciseUpdated = exerciseService.updateExercise(id, exerciseDto);
            return ResponseEntity.status(HttpStatus.OK).body(exerciseUpdated);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exercise not found please verify the id");
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('EXERCICIO_DELETE')")
    public ResponseEntity<Object> deleteExercise(@PathVariable UUID id){
        try{
            exerciseService.deleteExercise(id);
            return ResponseEntity.status(HttpStatus.OK).body("Exercise deleted successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exercise not found please verify the id");
        }
    }

}
