package com.gridu.store.model;


import com.gridu.store.constraint.Role;
import lombok.Data;



import javax.persistence.*;
import java.time.Instant;

import static com.gridu.store.constraint.Role.USER;

@Entity
@Data
@Table(name="accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role = USER;
    private Instant created;

    public Account(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.created = Instant.now();
    }



}
