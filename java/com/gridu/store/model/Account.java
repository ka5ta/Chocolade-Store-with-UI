package com.gridu.store.model;
import com.gridu.store.constraint.Role;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.time.Instant;
import java.util.*;

import static com.gridu.store.constraint.Role.USER;

@Entity
@Data
@Table(name = "accounts")
public class Account implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role = USER;

    @OneToMany(mappedBy = "account")
    private List<BasketProduct> basketProducts;
    private Instant created;
    private Instant updated;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;


    public Account() {
        this.created = Instant.now();
    }

    public Account(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.created = Instant.now();
        this.updated = null;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", created=" + created +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(this.role.name()));
        return roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }


}
