package br.com.treinow.controller;


import br.com.treinow.dtos.AdminsUserCreateDto;
import br.com.treinow.responsedto.UserResponseDto;
import br.com.treinow.dtos.UserRoleUpdateDto;
import br.com.treinow.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/users")
public class AdminController {

    @Autowired
    private UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDto> createUserByAdmin (@Valid @RequestBody AdminsUserCreateDto dto){
        UserResponseDto createdUser = userService.createUserByAdmin(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/{userId}/role")
    @PreAuthorize("hasAuthority('USER_MANAGE_STAFF')")
    public ResponseEntity<UserResponseDto> updateUserRole(@PathVariable UUID userId,
                                                          @Valid @RequestBody UserRoleUpdateDto dto){
        UserResponseDto updatedUser = userService.updateUserRole(userId, dto);
        return ResponseEntity.ok(updatedUser);
    }

}
