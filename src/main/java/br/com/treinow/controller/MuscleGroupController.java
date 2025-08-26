package br.com.treinow.controller;

import br.com.treinow.dtos.MuscleGroupDto;
import br.com.treinow.models.entities.MuscleGroupEntity;
import br.com.treinow.service.MuscleGroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/workout/musclegroup")
public class MuscleGroupController {

    @Autowired
    private MuscleGroupService muscleGroupService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ATRIBUTO_CREATE')")
    public MuscleGroupEntity createMuscleGroup(@RequestBody @Valid MuscleGroupDto muscleGroupDto){
        var createdMuscleGroup = muscleGroupService.createMuscleGroup(muscleGroupDto);
        return createdMuscleGroup;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ATRIBUTO_READ')")
    public List<MuscleGroupEntity> getAllMuscleGroup(){
        return muscleGroupService.getAllMuscleGroup();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findMuscleGroupById(@PathVariable(value = "id")UUID id){
        try{
            var muscleGroup = muscleGroupService.findMuscleGroupById(id);
            return ResponseEntity.status(HttpStatus.OK).body(muscleGroup);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Muscle group not found please verify the id");
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ATRIBUTO_UPDATE')")
    public ResponseEntity<Object> updateMuscleGroup(@PathVariable UUID id,
                                                    @RequestBody @Valid MuscleGroupDto muscleGroupDto){
        try{
            var muscleGroupUpdated = muscleGroupService.updateMuscleGroup(id, muscleGroupDto);
            return ResponseEntity.status(HttpStatus.OK).body(muscleGroupUpdated);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Muscle group not found please verify the id");
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ATRIBUTO_DELETE')")
    public ResponseEntity<Object> deleteMuscleGroup(@PathVariable UUID id){
        try{
            muscleGroupService.deleteMuscleGroup(id);
            return ResponseEntity.status(HttpStatus.OK).body("Muscle group deleted successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Muscle group not found please verify the id");
        }
    }

}
