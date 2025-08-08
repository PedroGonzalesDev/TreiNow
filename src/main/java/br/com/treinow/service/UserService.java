package br.com.treinow.service;

import br.com.treinow.dtos.UserDto;
import br.com.treinow.models.entities.AddressEntity;
import br.com.treinow.models.entities.RoleEntity;
import br.com.treinow.models.entities.UserEntity;
import br.com.treinow.repositories.jpa.AddressRepository;
import br.com.treinow.repositories.jpa.RoleRepository;
import br.com.treinow.repositories.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private RoleRepository roleRepository;

    //Regra de negocio metodo POST - cria usuario
    public UserEntity createUser(@Valid UserDto userDto){
        var userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        if (userEntity .getIsActive() == null) { userEntity.setIsActive(true);}

        var addressEntity = new AddressEntity();
        BeanUtils.copyProperties(userDto.address(), addressEntity);
        userEntity.setAddress(addressEntity);

        RoleEntity role = roleRepository.findById(userDto.roleId()).
                orElseThrow(() -> new RuntimeException("Role not found"));

        userEntity.setRole(role);

        return userRepository.save(userEntity);
    }

    //Regra de negocio metodo GET ALL - Puxa todos os usuarios
    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    //Regra de negocio metodo GET POR ID - Puxa o usuario do ID selecionado
    public Optional<UserEntity> findById(UUID id){
        return userRepository.findById(id);
    }

    //Regra de negocio metodo GET por NOME - puxa o usuario pelo nome selecionado
    public List<UserEntity> findByName(String name){
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    //Regra de negocio metodo UPDATE usuario - Atualiza campo desejado do usuario
    public UserEntity updateUser(UUID id, @Valid UserDto userDto){
        var user = userRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(userDto, user, "id");
        return userRepository.save(user);
    }

    //Regra de negocio metodo DELETE - dele o usuario do ID selecionado
    public void deleteUser (UUID id){
        var user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }

}
