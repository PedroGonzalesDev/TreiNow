package br.com.treinow.controller;

import br.com.treinow.dtos.RolePermissionDto;
import br.com.treinow.models.entities.RolePermissionEntity;
import br.com.treinow.service.RolePermissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roles/permission")
public class RolePermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RolePermissionEntity createRolePermission(@RequestBody @Valid RolePermissionDto rolePermissionDto){
        var createdRolePermission = rolePermissionService.createRolePermission(rolePermissionDto);
        return createdRolePermission;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RolePermissionEntity> getAllRolePermissions(){
        return rolePermissionService.getAllRolePermission();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findPermissionById(@PathVariable(value = "id") UUID id){
        try{
            var rolePermission = rolePermissionService.findRolePermissionById(id);
            return ResponseEntity.status(HttpStatus.OK).body(rolePermission);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role permission not found please verify the id");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMuscleGroup(@PathVariable UUID id,
                                                    @RequestBody @Valid RolePermissionDto rolePermissionDto){
        try{
            var rolePermission = rolePermissionService.updateRolePermission(id, rolePermissionDto);
            return ResponseEntity.status(HttpStatus.OK).body(rolePermission);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role permission not found please verify the id");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRolePermission(@PathVariable UUID id){
        try{
            rolePermissionService.deleteRolePermission(id);
            return ResponseEntity.status(HttpStatus.OK).body("Role permission deleted successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role permission not found please verify the id");
        }
    }

}
