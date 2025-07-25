package br.com.treinow.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String email;
    private String password;
    private Long phone;
    private Long identityNumber;
//    @OneToOne
//    @Column(name = "address_id")
    private Long AddressId;
    private Long roleId;
    private Boolean isActive;

    //Date time in case of calculations
    private String createdAt;
    private String lastLogin;



}
