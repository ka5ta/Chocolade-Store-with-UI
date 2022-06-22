package com.gridu.store.repository;

import com.gridu.store.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    List<Account> findAll();
    Optional<Account> findByEmailIgnoreCase(String email);
}
