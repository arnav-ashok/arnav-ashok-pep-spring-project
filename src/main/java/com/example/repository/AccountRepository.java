package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import com.example.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
    //1. Process new user registrations with a username/password
    Optional<Account> findByUsername(String username);
    //2. Process user logins that match the DB username/password
    //No need for any method here because the service layer calls findByUsername and validates the password
    
}
