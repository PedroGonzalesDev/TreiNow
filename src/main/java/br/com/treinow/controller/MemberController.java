package br.com.treinow.controller;


import br.com.treinow.dtos.MemberDto;
import br.com.treinow.models.entities.MemberEntity;
import br.com.treinow.service.MemberService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberEntity> createMember(@RequestBody @Valid MemberDto memberDto){
        var createdMember = memberService.createMember(memberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMember);
    }
}
