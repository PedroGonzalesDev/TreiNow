package br.com.treinow.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "membership")
public class MembershipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String durationType;
    private String description;
    private Long duration;
    private Double price;


}
