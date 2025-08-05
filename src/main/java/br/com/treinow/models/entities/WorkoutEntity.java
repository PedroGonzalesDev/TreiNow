package br.com.treinow.models.entities;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.UUID;

@Data
@Entity
@Table(name = "workout")
public class WorkoutEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private UUID workoutExerciseId;
    private String observation;
    private String createdBy;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private String createdAt;
    @UpdateTimestamp
    @Column(nullable = false)
    private String lastUpdateAt;
}
