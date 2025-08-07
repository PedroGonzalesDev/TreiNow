package br.com.treinow.service;

import br.com.treinow.dtos.PermissionDto;
import br.com.treinow.models.entities.PermissionEntity;
import br.com.treinow.repositories.jpa.PermissionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public PermissionEntity createPermission(@Valid PermissionDto permissionDto){
        var permissionEntity = new PermissionEntity();
        BeanUtils.copyProperties(permissionDto, permissionEntity);
        return permissionRepository.save(permissionEntity);
    }

    public List<PermissionEntity> getAllPermissions(){
        return permissionRepository.findAll();
    }

    public Optional<PermissionEntity> findPermissionById(UUID id){
        return permissionRepository.findById(id);
    }

    public PermissionEntity updatePermission(UUID id, @Valid PermissionDto permissionDto){
        var permission = permissionRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(permissionDto,permission, "id");
        return permissionRepository.save(permission);
    }

    public void deletePermission(UUID id){
        var permission = permissionRepository.findById(id).orElseThrow();
        permissionRepository.delete(permission);
    }

}
