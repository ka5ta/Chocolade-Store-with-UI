package com.gridu.store.controller;


import com.gridu.store.model.Account;
import com.gridu.store.repository.AccountRepository;
import com.gridu.store.service.AccountService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("users")
public class AccountController {

    private final AccountService service;
    private final AccountRepository repository;

    public AccountController(AccountService service, AccountRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping("/signup")
    public String showSignUpForm(Account account) {
        return "add-user";
    }

    @GetMapping
    public List<Account> all() {
        return service.findAll();
    }

    @Transactional
    public Account add(Account account) {
        repository.save(account);
        return account;
    }

    @GetMapping("/{id}")
    public Account get(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found for id: " + id)
        );
    }

    @Transactional
    public Map<String, Boolean> delete(@PathVariable Long id) {
        Account account = get(id);
        repository.delete(account);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
