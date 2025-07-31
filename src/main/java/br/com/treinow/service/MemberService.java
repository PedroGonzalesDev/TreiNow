package br.com.treinow.service;

import br.com.treinow.dtos.MemberDto;
import br.com.treinow.models.entities.MemberEntity;
import br.com.treinow.repositories.jpa.MemberRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public MemberEntity createMember(@Valid MemberDto memberDto) {
        MemberEntity memberEntity = new MemberEntity();
        BeanUtils.copyProperties(memberDto, memberEntity);
        return memberRepository.save(memberEntity);
    }
}
