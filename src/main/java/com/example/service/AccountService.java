package com.example.service;

import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import com.example.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.exception.CustomException;

@Service
public class AccountService {
    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    //1. Process new user registrations with a username/password
    public Account persistAccount(Account account){
        Optional<Account> queriedAccount=accountRepository.findByUsername(account.getUsername());
        if(queriedAccount.isPresent()){
            throw new OtherException("Account already exists.");
        }
        if(account == null){
            throw new OtherException("Account cannot be null.");
        }
        if (account.getUsername() == null ||!(account.getUsername().length() > 0)||account.getUsername().trim().isEmpty()){
            throw new OtherException("Username must be valid.");
        }
        if(account.getPassword() == null ||!(account.getPassword().length() >= 4)||account.getPassword().trim().isEmpty()){
            throw new OtherException("Password must be greater than 4 and not empty.");
        }
        return accountRepository.save(account);
    }
    
    //2. Process user logins that match the DB username/password
    public Account loginAccount(Account account){
        String username = account.getUsername();
        if(account.getUsername() == null ||!(account.getUsername().length() > 0)||account.getUsername().trim().isEmpty()){
            throw new OtherException("Username must be valid");
        }
        String password= account.getPassword();
        if(account.getPassword() == null ||!(account.getPassword().length() >= 4)||account.getPassword().trim().isEmpty()){
            throw new OtherException("Password must be greater than 4 and not empty.");
        }
        Optional<Account> queriedAccount = accountRepository.findByUsername(username);
        if(queriedAccount.isPresent()){
            Account retrievedAccount=queriedAccount.get();
            if(retrievedAccount.getUsername().equals(username)&& retrievedAccount.getPassword().equals(password)){
                return retrievedAccount;
            }
        }
        throw new OtherException("Login Failed");
    }
}
