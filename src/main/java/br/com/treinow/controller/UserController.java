package br.com.treinow.controller;

import br.com.treinow.dtos.RoleDto;
import br.com.treinow.dtos.UserDto;
import br.com.treinow.responsedto.UserResponseDto;
import br.com.treinow.mapper.UserMapper;
import br.com.treinow.models.entities.UserEntity;
import br.com.treinow.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)//Metodo post - Cadastra usuario
    @PreAuthorize("hasAuthority('USER_MANAGE_STAFF')")
    public UserResponseDto createUser(@RequestBody @Valid UserDto userDto){
        var createdUser = userService.createUser(userDto);
        return createdUser;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER_MANAGE_STAFF')")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_MANAGE_STAFF')")//Metodo get by id, puxa o usuarios de ID selecionado
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable(value="id") UUID id){
        return userService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    @PreAuthorize("hasAuthority('USER_MANAGE_STAFF')")//Metodo get by name, puxa os usuarios pelo nome
    public ResponseEntity<List<UserResponseDto>> searchUsers(@RequestParam String name){
        List<UserResponseDto> users = userService.findByName(name);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserResponseDto> getMyProfile(@AuthenticationPrincipal UserEntity currentUser, RoleDto roleDto){
        UserResponseDto userProfile = userMapper.toUserResponseDto(currentUser);
        return ResponseEntity.ok(userProfile);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_MANAGE_STAFF')")//Metodo Update, atualiza o usuario enviando os dados obrigatorios no json
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable UUID id,
                                             @RequestBody @Valid UserDto userDto) {
        try{
            UserResponseDto userUpdated = userService.updateUser(id, userDto);
            return ResponseEntity.ok(userUpdated);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_MANAGE_STAFF')")//Metodo delete, deleta o usuario de id selecionado
    public ResponseEntity<Object> deleteUser(@PathVariable UUID id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
