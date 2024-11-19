package com.org.vetconnect.platform.iam.domain.model.aggregates;

import com.org.vetconnect.platform.iam.domain.model.entities.Role;
import com.org.vetconnect.platform.profiles.domain.model.aggregates.VetCenter;
import com.org.vetconnect.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
public class User extends AuditableAbstractAggregateRoot<User> {
    @NotBlank
    @Getter
    @Column(unique = true)
    private String email;

    @Getter
    @NotBlank
    @Size(max = 120)
    private String password;

    @Getter
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private VetCenter vetCenter;  // Si el rol es VETERINARY, tendrá un VetCenter asociado.

    public User() {
        this.roles = new HashSet<>();
    }
    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.roles = new HashSet<>();
    }

    public User(String email, String password, List<Role> roles) {
        this(email, password);
        addRoles(roles);
    }

    public User addRole(Role role) {
        this.roles.add(role);
        return this;
    }

    public User addRoles(List<Role> roles) {
        //var validatedRoles = Role.validateRoleSet(roles);
        this.roles.addAll(roles);
        return this;
    }
}
