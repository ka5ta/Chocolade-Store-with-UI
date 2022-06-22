package com.gridu.store.service;

import com.gridu.store.dto.PasswordChangeDTO;
import com.gridu.store.dto.RegisterAccountDTO;
import com.gridu.store.model.Account;
import com.gridu.store.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;

import java.time.Instant;

import static com.gridu.store.constraint.Role.USER;


@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService implements UserDetailsService {

    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.warn("User is trying to signin with email: " + email);

        Account account = repository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User " + email + " doesn't exist in database");
                });
        logger.info("User found in directory.");
        return new User(account.getEmail(), account.getPassword(), account.getAuthorities());
    }

    public Account findByEmailOrThrow(String email) {
        logger.info("Fetching user by EMAIL");
        return repository.findByEmailIgnoreCase(email).orElseThrow(
                () -> new ResourceNotFoundException("User not found for email: " + email)
        );
    }

    public void saveAccount(Account account) {
        logger.info("Saving account: {} to database", account.getEmail());
        repository.save(account);
    }


    //todo {1} cant be transactional
    public Account createAccountFromRegisterAccountDTO(RegisterAccountDTO registerAccountDTO) throws AccountException {
        String email = registerAccountDTO.getEmail();
        String password = registerAccountDTO.getPassword();
        String repeatedPassword = registerAccountDTO.getRepeated();

        //checks if password and repeated password are equal, if not throws.
        areSamePasswords(password, repeatedPassword, false);

        Account account = new Account();
        account.setEmail(email);
        account.setRole(USER);
        account.setPassword(passwordEncoder.encode(password));

        return account;
    }

    public boolean areSamePasswords(String password1, String password2, Boolean isSecondEncoded) throws AccountException {

        if (isSecondEncoded) {
            if (passwordEncoder.matches(password1, password2)) {
                return true;
            }

        } else {
            if (password1.equals(password2)) {
                return true;
            }
        }

        throw new AccountException("Passwords doesn't match.");
    }

    //todo {1} cant be transactional
    public void register(Account accountToRegister) throws AccountException {
        try {
            if (repository.findByEmailIgnoreCase(accountToRegister.getEmail()).isPresent()) {
                throw new AccountException("Account already exists");
            }
            saveAccount(accountToRegister);
        } catch (DataIntegrityViolationException e) {
            throw new AccountException("Account already exists.");
        }
    }

    public Account updateAccountWithNewPassword(PasswordChangeDTO passwordChangeDTO, Account accountToUpdate) throws AccountException {
        String oldPassword = passwordChangeDTO.getOldPassword();
        String repeated = passwordChangeDTO.getRepeated();
        String newPassword = passwordChangeDTO.getNewPassword();

        //compare repeated passwords match or else throw
        try {
            areSamePasswords(newPassword, repeated, false);
        } catch (AccountException e) {
            throw new AccountException("Your new password and repeated password doesn't match. Try again.");
        }

        //compare old password to stored password
        try {
            areSamePasswords(oldPassword, accountToUpdate.getPassword(), true);
        } catch (AccountException e) {
            throw new AccountException("Your old password is incorrect. Try again");
        }

        //changes password successfully
        String newPasswordEncoded = passwordEncoder.encode(newPassword);
        accountToUpdate.setPassword(newPasswordEncoded);
        accountToUpdate.setUpdated(Instant.now());
        return accountToUpdate;
    }

}
