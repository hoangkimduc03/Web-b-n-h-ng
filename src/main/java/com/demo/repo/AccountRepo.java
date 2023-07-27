package com.demo.repo;

import com.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, String> {
    Optional<Account> findByUsernameAndPassword(String un, String pwd);
}
