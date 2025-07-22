package br.com.treinow.models.entities;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;
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
    private Long AddressId;
    private Long roleId;
    private Boolean isActive;


//    @Column(name = "created_at")
    private String createdAt;
//    @Column(name = "last_login")
    private String lastLogin;



}
