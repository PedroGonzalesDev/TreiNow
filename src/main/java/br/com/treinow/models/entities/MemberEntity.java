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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membership_id")
    private MembershipEntity membershipEntity;
    private String payment;
    private String gender;
    private String weight;
    private String height;
    private String State;

    //Date time in case of calculations
    private String subscriptionStartDate;
    private String subscriptionEndDate;

}