package br.com.treinow.models.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "workout")
public class WorkoutEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private Long workoutExerciseId;
    private String observation;

    //Date time in case of calculations
    private String createdBy;
    private String createdAt;
    private String lastUpdateAt;
}
