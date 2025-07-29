package br.com.treinow.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;
    private String name;
    private String email;
    @Column(nullable = false)
    private String password;
    private Long phone;
    private Long identityNumber;
//    @OneToOne
//    @Column(name = "address_id")
    private Long addressId;
    private Long roleId;
    private Boolean isActive;

    //Date time in case of calculations
    private String createdAt;
    private String lastLogin;



}
