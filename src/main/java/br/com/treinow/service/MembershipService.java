package br.com.treinow.service;

import br.com.treinow.dtos.MembershipDto;
import br.com.treinow.mapper.MembershipMapper;
import br.com.treinow.models.entities.MembershipEntity;
import br.com.treinow.repositories.jpa.MembershipRepository;
import br.com.treinow.responsedto.MembershipResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;
    @Autowired
    private MembershipMapper membershipMapper;

    public MembershipResponseDto createMembership(@Valid MembershipDto membershipDto){
        MembershipEntity membershipEntity = new MembershipEntity();
        BeanUtils.copyProperties(membershipDto, membershipEntity);
        MembershipEntity createdMembership = membershipRepository.save(membershipEntity);
        return membershipMapper.toMembershipResponseDto(createdMembership);
    }

    public List<MembershipResponseDto> getAllMembership(){
        return membershipMapper.toMembershipResponseDtoLit(membershipRepository.findAll());
    }

    public MembershipResponseDto updateMembership(UUID id, @Valid MembershipDto membershipDto){
        MembershipEntity membership = membershipRepository.findById(id).orElseThrow(() -> new RuntimeException("Membership not found"));
        BeanUtils.copyProperties(membershipDto, membership, "id");
        MembershipEntity updatedMembership = membershipRepository.save(membership);
        return membershipMapper.toMembershipResponseDto(updatedMembership);
    }

    public void deleteMembership(UUID id){
        var membership = membershipRepository.findById(id).orElseThrow();
        membershipRepository.delete(membership);
    }
}
