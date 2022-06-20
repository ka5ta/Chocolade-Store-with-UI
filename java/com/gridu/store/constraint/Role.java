package com.gridu.store.constraint;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;


public enum Role implements GrantedAuthority {
    USER("USER"), ADMIN("ADMIN");

    final String value;

    Role(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + this.value;
    }
}
