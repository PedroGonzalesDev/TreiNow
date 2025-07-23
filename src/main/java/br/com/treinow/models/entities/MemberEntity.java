package br.com.treinow.models.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "members")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Long membershipId;
    private String payment;
    private String gender;
    private String weight;
    private String height;

    //Date time in case of calculations
    private String subcriptionStartDate;
    private String subscriptionEndDate;

}