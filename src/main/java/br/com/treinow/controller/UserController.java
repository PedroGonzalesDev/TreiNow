package br.com.treinow.controller;

import br.com.treinow.dtos.UserDto;
import br.com.treinow.models.entities.UserEntity;
import br.com.treinow.repositories.jpa.UserJpa;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    UserJpa userJpa;

    @PostMapping("/users")
    public ResponseEntity<UserEntity> createUser(@RequestBody @Valid UserDto userDto){
        var userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(userJpa.save(userEntity));

    }
    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userJpa.findAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value="id") UUID id){
        Optional<UserEntity> userO = userJpa.findById(id);
        if(userO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userO.get());
    }
}
