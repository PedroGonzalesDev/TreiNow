package br.com.treinow.service;

import br.com.treinow.dtos.RoleDto;
import br.com.treinow.dtos.UserDto;
import br.com.treinow.dtos.UserResponseDto;
import br.com.treinow.mapper.UserMapper;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;

    //Regra de negocio metodo POST - cria usuario
    public UserResponseDto createUser(UserDto userDto){
        var userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        if (userEntity .getIsActive() == null) { userEntity.setIsActive(true);}

        String encryptedPassword = passwordEncoder.encode(userDto.password());
        userEntity.setPassword(encryptedPassword);

        var addressEntity = new AddressEntity();
        BeanUtils.copyProperties(userDto.address(), addressEntity);
        userEntity.setAddress(addressEntity);

        RoleEntity defaultRole = roleRepository.findByName("ROLE_ALUNO")
                        .orElseThrow(() -> new RuntimeException("Erro: Role 'ROLE_ALUNO' não encontrada"));

        userEntity.setRole(defaultRole);

        UserEntity savedUser = userRepository.save(userEntity);

        return userMapper.toUserResponseDto(savedUser);
    }

    //Regra de negocio metodo GET ALL - Puxa todos os usuarios
    public List<UserResponseDto> getAllUsers() {
        return userMapper.toUserResponseDtoList(userRepository.findAll());
    }

    //Regra de negocio metodo GET POR ID - Puxa o usuario do ID selecionado
    public Optional<UserResponseDto> findById(UUID id) {
        return userRepository.findById(id).map(userMapper::toUserResponseDto);
    }

    //Regra de negocio metodo GET por NOME - puxa o usuario pelo nome selecionado
    public List<UserResponseDto> findByName(String name) {
        return userMapper.toUserResponseDtoList(userRepository.findByNameContainingIgnoreCase(name));
    }


    //Regra de negocio metodo UPDATE usuario - Atualiza campo desejado do usuario
    public UserResponseDto updateUser(UUID id, @Valid UserDto userDto) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        BeanUtils.copyProperties(userDto, user, "id");

        if (userDto.password() != null && !userDto.password().isBlank()) {
            String encryptedPassword = passwordEncoder.encode(userDto.password());
            user.setPassword(encryptedPassword);
        }

        UserEntity updatedUser = userRepository.save(user);

        return userMapper.toUserResponseDto(updatedUser);
    }

    //Regra de negocio metodo DELETE - dele o usuario do ID selecionado
    public void deleteUser (UUID id){
        var user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }

}
