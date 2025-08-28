package br.com.treinow.models.entities;

import br.com.treinow.models.entities.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    @Column(unique = true)
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @CPF
    @Column(unique = true, length = 14)
    private String cpf;
    private String phone;
    @ManyToOne(cascade = CascadeType.ALL) //salva o address junto
    @JoinColumn(name = "address_id")
    private AddressEntity address;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private RoleEntity role;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;
    private String activationToken; //Token para ativar a conta / definir a senha
    private LocalDateTime tokenExpiryDate; //Data de expiração do token
    private boolean  forcePasswordChange; //Forçar a troca no primeiro login

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();

        authorities.add(new SimpleGrantedAuthority(this.role.getName()));

        authorities.addAll(
                this.role.getPermissions().stream()
                        .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                        .collect(Collectors.toList())

        );
        return authorities;
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
        return this.status == UserStatus.ACTIVE; //Habilita se o campo isActive for true
    }

}
