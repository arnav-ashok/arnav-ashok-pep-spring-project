package com.example.service;

import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import com.example.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    //1. Process new user registrations with a username/password
    public Account persistAccount(Account account){
        return accountRepository.save(account);
    }
    
    //2. Process user logins that match the DB username/password
    public Account loginAccount(Account account){
        String username = account.getUsername();
        String password= account.getPassword();
        Optional<Account> queriedAccount = accountRepository.findByUsername(username);
        if(queriedAccount.isPresent()){
            Account got=queriedAccount.get();
            if(got.getUsername().equals(username)&& got.getPassword().equals(password)){
                return got;
            }

        }else{
            return null;
        }
    }
}
