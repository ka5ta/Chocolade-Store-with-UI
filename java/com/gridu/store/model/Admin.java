package com.gridu.store.model;


import com.gridu.store.constraint.Role;

public class Admin extends Account {

    public Admin(String email, String password, Role role) {
        super(email, password, role);
    }
}
