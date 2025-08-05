package br.com.treinow.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    @CPF
    @Column(unique = true, length = 14)
    private String cpf;
    private String phone;
    private Long addressId;
    private Long roleId;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private String createdAt;
    @UpdateTimestamp
    @Column(nullable = false)
    private String lastLogin;
    @Column(nullable = false)
    private Boolean isActive = true;
    private void setDefaultActive(){
        if(this.isActive == null){
            this.isActive = true;
        }
    }



}
