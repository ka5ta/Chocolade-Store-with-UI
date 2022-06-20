package com.gridu.store.service;

import com.gridu.store.model.Account;
import com.gridu.store.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static com.gridu.store.constraint.Role.USER;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountService implements UserDetailsService {

    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = repository.findByEmail(email)
                .orElseThrow(() -> {

                    throw new UsernameNotFoundException("User " + email + " doesn't exist in database");
                });
        return new User(account.getEmail(), account.getPassword(), account.getAuthorities());
    }


    public Account findById(Long id) {
        logger.info("Fetching user by ID");
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found for id: " + id)
        );
    }

    public Account findByEmail(String email) {
        logger.info("Fetching user by EMAIL");
        return repository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User not found for email: " + email)
        );
    }

    public void saveAccount(Account account) {
        logger.debug("Save account: {} to database", account);
        repository.save(account);
    }

    public void register(String email, String password, String repeatedPassword) throws AccountException {

        Optional<Account> byEmail = repository.findByEmail(email);
        if (byEmail.isPresent()) {
            throw new AccountException("You have an account. Please Sign in.");
        } else if (!password.equals(repeatedPassword)) {
            throw new AccountException("Your password doesn't match. Try again");
        } else {
            Account account = new Account();
            account.setEmail(email);
            account.setRole(USER);
            account.setPassword(passwordEncoder.encode(password));
            saveAccount(account);
        }
    }
}
