package com.example.springpostgres.repositories;

import com.example.springpostgres.models.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {
    Account findAccountByEmail(String email);
}
