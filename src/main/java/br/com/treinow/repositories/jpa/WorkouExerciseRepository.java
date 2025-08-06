package br.com.treinow.repositories.jpa;

import br.com.treinow.models.entities.WorkoutExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WorkouExerciseRepository extends JpaRepository<WorkoutExerciseEntity, UUID> {
}
