package br.com.treinow.repositories.jpa;


import br.com.treinow.models.entities.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, UUID> {
    //List<MemberEntity> findByNameContainingIgnoreCase(String name);
}
