package br.com.treinow.controller;

import br.com.treinow.dtos.PermissionDto;
import br.com.treinow.models.entities.PermissionEntity;
import br.com.treinow.service.PermissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roles/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PermissionEntity createPermission(@RequestBody @Valid PermissionDto permissionDto){
        var createdPermission = permissionService.createPermission(permissionDto);
        return createdPermission;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<PermissionEntity> getAllPermissions(){
        return permissionService.getAllPermissions();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> findPermissionById(@PathVariable(value = "id")UUID id){
        try{
            var permission = permissionService.findPermissionById(id);
            return ResponseEntity.status(HttpStatus.OK).body(permission);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Permission not found please verify the id");
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> updatePermission (@PathVariable UUID id,
                                                    @RequestBody @Valid PermissionDto permissionDto){
        try{
            var permission = permissionService.updatePermission(id, permissionDto);
            return ResponseEntity.status(HttpStatus.OK).body(permission);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Permission not found please verify the id");
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deletePermission(@PathVariable UUID id){
        try {
            permissionService.deletePermission(id);
            return ResponseEntity.status(HttpStatus.OK).body("Permission deleted successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Permission not found please verify the id");
        }
    }

}
