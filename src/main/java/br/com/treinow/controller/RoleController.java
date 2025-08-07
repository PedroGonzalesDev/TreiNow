package br.com.treinow.controller;

import br.com.treinow.dtos.RoleDto;
import br.com.treinow.models.entities.RoleEntity;
import br.com.treinow.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleEntity createRole (@RequestBody @Valid RoleDto roleDto){
        var createdRole = roleService.createRole(roleDto);
        return createdRole;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RoleEntity> getAllRoles(){
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findRoleById(@PathVariable(value = "id")UUID id){
        try{
            var role = roleService.findRoleById(id);
            return ResponseEntity.status(HttpStatus.OK).body(role);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found please verify the id");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRole(@PathVariable UUID id,
                                             @RequestBody @Valid RoleDto roleDto){
        try{
            var role = roleService.updateRole(id, roleDto);
            return ResponseEntity.status(HttpStatus.OK).body(role);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found please verify the id");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRole(@PathVariable UUID id){
        try{
            roleService.deleteRole(id);
            return ResponseEntity.status(HttpStatus.OK).body("Role deleted successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found please verify the id");
        }
    }
}
