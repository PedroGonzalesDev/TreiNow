package br.com.treinow.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;
    @CPF
    @Column(unique = true, length = 14)
    private String cpf;
    private String phone;
    @ManyToOne(cascade = CascadeType.ALL) //salva o address junto
    @JoinColumn(name = "address_id")
    private AddressEntity address;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private String createdAt;
    private String lastLogin;
    @Column(nullable = false)
    private Boolean isActive = true;
    private void setDefaultActive(){
        if(this.isActive == null){
            this.isActive = true;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        //Retorna a lista de permissões/roles
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase()));
    }

    @Override
    public String getUsername(){
        return email; //username para o spring security é o email
    }

    @Override
    public boolean isAccountNonExpired(){
        return true; //Conta nunca expira
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true; //Credenciais nunca expiram
    }

    @Override
    public boolean isEnabled(){
        return this.isActive; //Habilita se o campo isActive for true
    }


}
