package br.com.treinow.controller;

import br.com.treinow.dtos.MembershipDto;
import br.com.treinow.models.entities.MembershipEntity;
import br.com.treinow.service.MembershipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    
}
