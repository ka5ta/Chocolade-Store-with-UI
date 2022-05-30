package com.gridu.store.service;

import com.gridu.store.model.Account;
import com.gridu.store.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public List<Account> findAll(){
        return new ArrayList<>(repository.findAll());
    }
}
