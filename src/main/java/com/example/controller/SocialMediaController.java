package com.example.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;

import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @RestController
public class SocialMediaController {
    @Autowired
    MessageService messageservice;

    @Autowired
    AccountService accountservice;

    //1. Process new user registrations with a username/password
    @PostMapping("/register")
    public Account registerAccount(@RequestBody Account a){

    }

    //2. Process user logins that match the DB username/password
    @PostMapping("/login")
    public Account loginAccount(@RequestBody Account a){

    }


    //3. Process the creation of new messages
    @PostMapping("/messages")
    public Message createMessage(@RequestBody Message m){

    }
    
    //4. Retrieve all messages
    @GetMapping("/messages")
    public List<Message> getAllMessages(){

    }

    //5. Retrieve a message by ID
    @GetMapping("/messages/{message_id}")
    public Message getMessageById(@PathVariable ("message_id") long id){

    }
    

    //6. Delete a message identified by ID
    @DeleteMapping("/messages/{message_id}")
    public int deleteMessageById(@PathVariable ("message_id") long id){

    }


    
    //7. Update a message text identified by ID
    @PatchMapping("/messages/{message_id}")
    public int updateMessageById(@PathVariable ("message_id") long id){

    }

    //8. Retrieve all messages by an account/user 
    @GetMapping("/accounts/{account_id}/messages")
    public List<Message> getMessagesByPostedBy(@PathVariable ("account_id") long id){

    }
    

}
