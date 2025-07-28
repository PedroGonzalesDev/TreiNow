package br.com.treinow.controller;

import br.com.treinow.dtos.UserDto;
import br.com.treinow.models.entities.UserEntity;
import br.com.treinow.repositories.jpa.UserRepository;
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
    UserRepository userRepository;

    @PostMapping("/users")  //@PostMappin define que é um Post/Create | /users define a URI
    public ResponseEntity<UserEntity> createUser(@RequestBody @Valid UserDto userDto){
        var userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(userEntity));

    }
    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value="id") UUID id){
        Optional<UserEntity> userO = userRepository.findById(id);
        if(userO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userO.get());
    }

    @PutMapping("/users/{id}") //@PutMapping define que é um HTTP PUT | {id} Path variable será o id recebido pela URI
    //Declara o method do controller para tratar Retorna um response entity object que permite tratar com códigos HTTP
    public ResponseEntity<Object> updateUser(@PathVariable(value="id") UUID id, //@PathVarivel - Pega valor id e coloca em uma varivel id tipo UUID
                                             @RequestBody @Valid UserDto userDto) { //@RequestBody diz que o JSON será convertido  userDto e @Valid ativa as validações do DTO
        Optional<UserEntity> userO = userRepository.findById(id);  //Utiliza repositorio jpa para buscar pelo id, metodo findby retorna um optional que pode conter um userEntity caso encontrado
        if (userO.isEmpty()) { //userO empty verifica se o usuario não foi encontrado e retorna 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        var userEntity = userO.get(); //cria uma variavel userEntity com as mesmas propriedades de userO
        BeanUtils.copyProperties(userDto, userEntity); //Copia os valores dos atributos userDto para userEntity
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(userEntity)); //Retorna status Ok e salva
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value="id") UUID id){
        Optional<UserEntity> userO = userRepository.findById(id);
        if(userO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        userRepository.delete(userO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted sucessfully");
    }

}
