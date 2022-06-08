package com.gridu.store.service;

import com.gridu.store.model.Account;
import com.gridu.store.repository.AccountRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class AccountService {


    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public List<Account> findAll(){
        return new ArrayList<>(repository.findAll());
    }

    public Account findById(Long id){
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found for id: " + id)
        );
    }

    public Optional<Account> findByEmail(String email){
        return repository.findByEmail(email);
    }

    @Transactional
    public Account add(Account account) {
        repository.save(account);
        return account;
    }

    public void delete(Account account) {
        repository.delete(account);
    }
}
