package br.com.treinow.repositories.jpa;

import br.com.treinow.models.entities.MuscleGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MuscleGroupRepository extends JpaRepository<MuscleGroupEntity, UUID> {
}
