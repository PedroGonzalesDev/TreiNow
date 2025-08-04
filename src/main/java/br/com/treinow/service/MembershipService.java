package br.com.treinow.service;

import br.com.treinow.dtos.MembershipDto;
import br.com.treinow.models.entities.MembershipEntity;
import br.com.treinow.repositories.jpa.MembershipRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    public MembershipEntity createMembership(@Valid MembershipDto membershipDto){
        MembershipEntity membershipEntity = new MembershipEntity();
        BeanUtils.copyProperties(membershipDto, membershipEntity);
        return membershipRepository.save(membershipEntity);
    }
}
