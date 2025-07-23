package br.com.treinow.models.entities;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.UUID;

@Data
@Entity
@Table(name = "workout_exercise")
public class WorkoutExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Long exerciseId;
    private Long sets;
    private Long repetitions;
    private Long weight;
    private String observation;

}
