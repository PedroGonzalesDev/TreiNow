package br.com.treinow.controller;

import br.com.treinow.dtos.UserDto;
import br.com.treinow.models.entities.UserEntity;
import br.com.treinow.repositories.jpa.UserJpa;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
