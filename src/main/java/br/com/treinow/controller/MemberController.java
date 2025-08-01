package br.com.treinow.controller;


import br.com.treinow.dtos.MemberDto;
import br.com.treinow.dtos.UserDto;
import br.com.treinow.models.entities.MemberEntity;
import br.com.treinow.service.MemberService;
import jakarta.validation.Valid;
import lombok.Data;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping //Metodo post - cria membro
    public ResponseEntity<MemberEntity> createMember(@RequestBody @Valid MemberDto memberDto){
        var createdMember = memberService.createMember(memberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMember);
    }

    @GetMapping //Metodo get, puxa todos os membros
    public ResponseEntity<List<MemberEntity>> getAllMembers(){
        return ResponseEntity.status(HttpStatus.OK).body(memberService.getAllMembers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMemberById(@PathVariable(value = "id") UUID id){
        return memberService.findById(id).<ResponseEntity<Object>>map(ResponseEntity::ok).
                orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member not found"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMember(@PathVariable UUID id,
                                             @RequestBody @Valid MemberDto memberDto) {
        try {
            var memberUpdated = memberService.updateMember(id, memberDto);
            return ResponseEntity.status(HttpStatus.OK).body(memberUpdated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member not found");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMember(@PathVariable UUID id){
        try{
            memberService.deleteMember(id);
            return ResponseEntity.status(HttpStatus.OK).body("Member deleted sucessfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member not found");
            }
        }
}
