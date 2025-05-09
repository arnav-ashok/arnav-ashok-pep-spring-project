package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.example.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
    //1. Process new user registrations with a username/password
    Optional<Account> findAccountByUsername(String username);
    //2. Process user logins that match the DB username/password
    //No need for any method here because the service layer calls findByUsername and validates the password
    
}
