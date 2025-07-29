package br.com.treinow.controller;

import br.com.treinow.dtos.UserDto;
import br.com.treinow.dtos.UserResponseDto;
import br.com.treinow.models.entities.UserEntity;
import br.com.treinow.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping  //@PostMappin define que é um Post/Create | /users define a URI
    public ResponseEntity<UserEntity> createUser(@RequestBody @Valid UserDto userDto){
        var createdUser = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);

    }
    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value="id") UUID id){
        return userService.findById(id).<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found."));
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserResponseDto>> searchUsers(@RequestParam String name){
        List<UserEntity> users = userService.findByName(name);
        List<UserResponseDto> response = users.stream().map(user -> new UserResponseDto(user.getId(), user.getName(), user.getEmail())).toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable UUID id,
                                             @RequestBody @Valid UserDto userDto) {
        try{
            var userUpdated = userService.updateUser(id, userDto);
            return ResponseEntity.status(HttpStatus.OK).body(userUpdated);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body("User deleted sucessfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not Found.");
        }
    }
}
