package br.com.treinow.service;

import br.com.treinow.dtos.MembershipDto;
import br.com.treinow.models.entities.MembershipEntity;
import br.com.treinow.repositories.jpa.MembershipRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Member;
import java.util.List;
import java.util.UUID;

@Service
public class MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    public MembershipEntity createMembership(@Valid MembershipDto membershipDto){
        MembershipEntity membershipEntity = new MembershipEntity();
        BeanUtils.copyProperties(membershipDto, membershipEntity);
        return membershipRepository.save(membershipEntity);
    }

    public List<MembershipEntity> getAllMembership(){
        return membershipRepository.findAll();
    }

    public MembershipEntity updateMembership(UUID id, @Valid MembershipDto membershipDto){
        var membership = membershipRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(membershipDto, membership, "id");
        return membershipRepository.save(membership);
    }

    public void deleteMembership(UUID id){
        var membership = membershipRepository.findById(id).orElseThrow();
        membershipRepository.delete(membership);
    }
}
