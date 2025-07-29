package br.com.treinow.service;

import br.com.treinow.dtos.UserDto;
import br.com.treinow.models.entities.UserEntity;
import br.com.treinow.repositories.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity createUser(@Valid UserDto userDto){
        var userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity, "id");
        return userRepository.save(userEntity);
    }

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<UserEntity> findById(Long id){
        return userRepository.findById(id);
    }

    public List<UserEntity> findByName(String name){
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    public UserEntity updateUser(Long id, @Valid UserDto userDto){
        var user = userRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(userDto, user, "id");
        return userRepository.save(user);
    }

    public void deleteUser (Long id){
        var user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }

}
