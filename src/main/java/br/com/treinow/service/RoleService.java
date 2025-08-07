package br.com.treinow.service;

import br.com.treinow.dtos.RoleDto;
import br.com.treinow.models.entities.RoleEntity;
import br.com.treinow.repositories.jpa.RoleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleEntity createRole (@Valid RoleDto roleDto){
        var roleEntity = new RoleEntity();
        BeanUtils.copyProperties(roleDto, roleEntity);
        return roleRepository.save(roleEntity);
    }

    public List<RoleEntity> getAllRoles(){
        return roleRepository.findAll();
    }

    public Optional<RoleEntity> findRoleById(UUID id){
        return roleRepository.findById(id);
    }

    public RoleEntity updateRole (UUID id, @Valid RoleDto roleDto){
        var role = roleRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(roleDto, role, "id");
        return roleRepository.save(role);
    }

    public void deleteRole(UUID id){
        var role = roleRepository.findById(id).orElseThrow();
        roleRepository.delete(role);
    }
}
