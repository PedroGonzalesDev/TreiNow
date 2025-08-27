package br.com.treinow.service;

import br.com.treinow.dtos.RoleDto;
import br.com.treinow.mapper.RoleMapper;
import br.com.treinow.models.entities.RoleEntity;
import br.com.treinow.repositories.jpa.RoleRepository;
import br.com.treinow.responsedto.RoleResponseDto;
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
    @Autowired
    private RoleMapper roleMapper;

    public RoleResponseDto createRole (RoleDto roleDto){
        var roleEntity = new RoleEntity();
        BeanUtils.copyProperties(roleDto, roleEntity);
        RoleEntity savedRole = roleRepository.save(roleEntity);
        return roleMapper.toRoleResponseDto(savedRole);
    }

    public List<RoleResponseDto> getAllRoles(){
        return roleMapper.toRoleReponseDtoList(roleRepository.findAll());
    }

    public Optional<RoleResponseDto> findRoleById(UUID id){
        return roleRepository.findById(id).map(roleMapper::toRoleResponseDto);
    }

    public RoleResponseDto updateRole (UUID id, @Valid RoleDto roleDto){
        var role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
        BeanUtils.copyProperties(roleDto, role, "id");

        RoleEntity updatedRole = roleRepository.save(role);
        return roleMapper.toRoleResponseDto(updatedRole);
    }

    public void deleteRole(UUID id){
        var role = roleRepository.findById(id).orElseThrow();
        roleRepository.delete(role);
    }
}
