package br.com.treinow.models.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Table(name = "members")
@NoArgsConstructor
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID userId;
    private Long membershipId;
    private String payment;
    private String gender;
    private String weight;
    private String height;
    private String State;

    //Date time in case of calculations
    private String subscriptionStartDate;
    private String subscriptionEndDate;

}