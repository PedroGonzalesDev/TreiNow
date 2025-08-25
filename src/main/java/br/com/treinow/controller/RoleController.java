package br.com.treinow.controller;

import br.com.treinow.dtos.RoleDto;
import br.com.treinow.mapper.RoleMapper;
import br.com.treinow.models.entities.RoleEntity;
import br.com.treinow.responsedto.RoleResponseDto;
import br.com.treinow.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMapper roleMapper;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_MANAGE')")
    @ResponseStatus(HttpStatus.CREATED)
    public RoleEntity createRole (@RequestBody @Valid RoleDto roleDto){
        var createdRole = roleService.createRole(roleDto);
        return createdRole;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_MANAGE')")
    public ResponseEntity<List<RoleResponseDto>> getAllRoles(){
        List<RoleResponseDto> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<RoleResponseDto> findRoleById(@PathVariable(value = "id")UUID id) {
        return roleService.findRoleById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
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
    @PreAuthorize("hasAuthority('ROLE_MANAGE')")
    public ResponseEntity<Object> deleteRole(@PathVariable UUID id){
        try{
            roleService.deleteRole(id);
            return ResponseEntity.status(HttpStatus.OK).body("Role deleted successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found please verify the id");
        }
    }
}
