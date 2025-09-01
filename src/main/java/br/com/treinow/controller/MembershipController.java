package br.com.treinow.controller;

import br.com.treinow.dtos.MembershipDto;
import br.com.treinow.models.entities.MembershipEntity;
import br.com.treinow.responsedto.MembershipResponseDto;
import br.com.treinow.service.MembershipService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/membership")
public class MembershipController {
    
    @Autowired
    private MembershipService membershipService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('MANAGE_MEMBERSHIP')")
    public MembershipResponseDto createMembership(@RequestBody @Valid MembershipDto membershipDto){
        var createdMembership= membershipService.createMembership(membershipDto);
        return createdMembership;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('MEMBERSHIP_READ')")
    public ResponseEntity<List<MembershipResponseDto>> getAllMembership(){
        List<MembershipResponseDto> memberships = membershipService.getAllMembership();
        return ResponseEntity.ok(memberships);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGE_MEMBERSHIP')")
    public ResponseEntity<MembershipResponseDto> updateMembership(@PathVariable UUID id,
                                                   @RequestBody @Valid MembershipDto membershipDto){
        try{
            var membershipUpdated = membershipService.updateMembership(id, membershipDto);
            return ResponseEntity.ok(membershipUpdated);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteMembership(@PathVariable UUID id){
        try{
            membershipService.deleteMembership(id);
            return ResponseEntity.status(HttpStatus.OK).body("Membership delete successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Membership not found verify id");
        }
    }
    
}
