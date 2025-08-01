package br.com.treinow.service;

import br.com.treinow.dtos.MemberDto;
import br.com.treinow.models.entities.MemberEntity;
import br.com.treinow.models.entities.UserEntity;
import br.com.treinow.repositories.jpa.MemberRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    //Regra de negocio metodo post
    public MemberEntity createMember(@Valid MemberDto memberDto) {
        MemberEntity memberEntity = new MemberEntity();
        BeanUtils.copyProperties(memberDto, memberEntity);
        return memberRepository.save(memberEntity);
    }

    public List<MemberEntity> getAllMembers(){
        return memberRepository.findAll();
    }

    public Optional<MemberEntity> findById(UUID id){
        return memberRepository.findById(id);
    }

//    public List<MemberEntity> findByName(String name){
//        return memberRepository.findByNameContainingIgnoreCase(name);
//    }
    public MemberEntity updateMember(UUID id, @Valid MemberDto memberDto){
        var member = memberRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(memberDto, member, "id");
        return memberRepository.save(member);
    }

    public void deleteMember (UUID id){
        var member = memberRepository.findById(id).orElseThrow();
        memberRepository.delete(member);
    }
}
