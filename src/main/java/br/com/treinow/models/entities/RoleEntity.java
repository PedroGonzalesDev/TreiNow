package br.com.treinow.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(exclude = "permissions")
@NoArgsConstructor
@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true, nullable = false)
    private String name;
    private String description;

    @ManyToMany(fetch = FetchType.EAGER) //Carrega as permissões junto com a role
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )

    private Set<PermissionEntity> permissions;

    public RoleEntity(String name, String description) {
        this.name = name;
        this.description = description;
        this.permissions = new HashSet<>(); // Garante que a coleção nunca será nula
    }

    public void addPermission(PermissionEntity permission) {
        if (this.permissions == null) {
            this.permissions = new HashSet<>();
        }
        this.permissions.add(permission);

        if (permission.getRoles() == null) {
            permission.setRoles(new HashSet<>());
        }
        permission.getRoles().add(this);
    }

}
