package br.com.treinow.service;

import br.com.treinow.dtos.MemberDto;
import br.com.treinow.mapper.MemberMapper;
import br.com.treinow.models.entities.MemberEntity;
import br.com.treinow.models.entities.MembershipEntity;
import br.com.treinow.models.entities.UserEntity;
import br.com.treinow.repositories.jpa.MemberRepository;
import br.com.treinow.repositories.jpa.MembershipRepository;
import br.com.treinow.responsedto.MemberResponseDto;
import br.com.treinow.utils.SubscriptionUtils;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MemberService {

    @Autowired private MemberRepository memberRepository;
    @Autowired private MemberMapper memberMapper;
    @Autowired private MembershipRepository membershipRepository;

    //Regra de negocio metodo post
    public MemberResponseDto createMember(MemberDto memberDto) {
        MemberEntity memberEntity = new MemberEntity();
        BeanUtils.copyProperties(memberDto, memberEntity);
        MemberEntity savedMember = memberRepository.save(memberEntity);
        return memberMapper.toMemberResponseDto(savedMember);
    }

    public List<MemberResponseDto> getAllMembers(){
        return memberMapper.toMemberResponseDtoList(memberRepository.findAll());
    }

    public Optional<MemberResponseDto> findById(UUID id){
        return memberRepository.findById(id).map(memberMapper::toMemberResponseDto);
    }

    public MemberResponseDto updateMember(UUID id, @Valid MemberDto memberDto){
        var member = memberRepository.findById(id).orElseThrow(()-> new RuntimeException("Member not found"));
        BeanUtils.copyProperties(memberDto, member, "id");
        if (memberDto.membershipId() != null && !memberDto.membershipId().equals(member.getMembershipEntity().getId())) {

            MembershipEntity newPlan = membershipRepository.findById(memberDto.membershipId())
                    .orElseThrow(() -> new RuntimeException("Novo plano não encontrado com o ID: " + memberDto.membershipId()));

            LocalDate newStartDate = LocalDate.now();

            LocalDate newEndDate = SubscriptionUtils.calculateEndDate(newStartDate, newPlan);

            member.setMembershipEntity(newPlan);
            member.setSubscriptionStartDate(String.valueOf(newStartDate));
            member.setSubscriptionEndDate(String.valueOf(newEndDate));
        }
        var updatedMember = memberRepository.save(member);
        return memberMapper.toMemberResponseDto(updatedMember);
    }

    public void deleteMember (UUID id){
        var member = memberRepository.findById(id).orElseThrow();
        memberRepository.delete(member);
    }
}
