package com.gridu.store.model;


import com.gridu.store.constraint.Role;
import lombok.Data;



import javax.persistence.*;
import java.time.Instant;
import java.util.List;

import static com.gridu.store.constraint.Role.USER;

@Entity
@Data
@Table(name="accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role = USER;

    @OneToMany(mappedBy = "account")
    private List<BasketProduct> basketProducts;
    private Instant created;


    public Account() {
    }

    public Account(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.created = Instant.now();
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
}
