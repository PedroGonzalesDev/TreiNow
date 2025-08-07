package br.com.treinow.service;

import br.com.treinow.dtos.RolePermissionDto;
import br.com.treinow.models.entities.RolePermissionEntity;
import br.com.treinow.repositories.jpa.RolePermissionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RolePermissionService {

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    public RolePermissionEntity createRolePermission(@Valid RolePermissionDto rolePermissionDto){
        var rolePermissionEntity = new RolePermissionEntity();
        BeanUtils.copyProperties(rolePermissionDto, rolePermissionEntity);
        return rolePermissionRepository.save(rolePermissionEntity);
    }

    public List<RolePermissionEntity> getAllRolePermission(){
        return rolePermissionRepository.findAll();
    }

    public Optional<RolePermissionEntity> findRolePermissionById(UUID id){
        return rolePermissionRepository.findById(id);
    }

    public RolePermissionEntity updateRolePermission(UUID id, @Valid RolePermissionDto rolePermissionDto){
        var rolePermission = rolePermissionRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(rolePermissionDto, rolePermission, "id");
        return rolePermissionRepository.save(rolePermission);
    }

    public void deleteRolePermission(UUID id){
        var rolePermission = rolePermissionRepository.findById(id).orElseThrow();
        rolePermissionRepository.delete(rolePermission);
    }
    
}
