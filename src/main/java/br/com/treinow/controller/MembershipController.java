package br.com.treinow.controller;

import br.com.treinow.dtos.MembershipDto;
import br.com.treinow.models.entities.MembershipEntity;
import br.com.treinow.service.MembershipService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public MembershipEntity createMembership(@RequestBody @Valid MembershipDto membershipDto){
        var createdMembership= membershipService.createMembership(membershipDto);
        return createdMembership;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MembershipEntity> getAllMembership(){
        return membershipService.getAllMembership();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMembership(@PathVariable UUID id,
                                                   @RequestBody @Valid MembershipDto membershipDto){
        try{
            var membershipUpdated = membershipService.updateMembership(id, membershipDto);
            return ResponseEntity.status(HttpStatus.OK).body(membershipUpdated);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Membership not found verify id");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMembership(@PathVariable UUID id){
        try{
            membershipService.deleteMembership(id);
            return ResponseEntity.status(HttpStatus.OK).body("Membership delete sucessfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Membership not found verify id");
        }
    }
    
}
