package com.example.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.UsernameAlreadyExistsException;
import com.example.exception.InvalidException;
import com.example.exception.UnauthorizedException;
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
    public ResponseEntity<Account> registerAccount(@RequestBody Account a){
        try{
            Account savedAccount = accountservice.persistAccount(a);
            return new ResponseEntity<>(savedAccount, HttpStatus.OK);
        }catch(UsernameAlreadyExistsException u){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }catch(InvalidException i){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    //2. Process user logins that match the DB username/password
    @PostMapping("/login")
    public ResponseEntity<Account> loginAccount(@RequestBody Account a){
        try{
            Account loggedAccount = accountservice.loginAccount(a);
            return new ResponseEntity<>(loggedAccount, HttpStatus.OK);
        }catch(UnauthorizedException u){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }


    //3. Process the creation of new messages
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message m){
        try{
            Message message = messageservice.persistMessage(m);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch(InvalidException i){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    
    //4. Retrieve all messages
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages(){
        return new ResponseEntity<>(messageservice.getAllMessages(), HttpStatus.OK);

    }

    //5. Retrieve a message by ID
    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getMessageById(@PathVariable ("message_id") long id){
        Message message= messageservice.getMessageById(id);
        return new ResponseEntity<>(message, HttpStatus.OK);

    }
    

    //6. Delete a message identified by ID
    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable ("message_id") long id){
        int indicator = messageservice.deleteMessageById(id);
        return new ResponseEntity<>(indicator, HttpStatus.OK);

    }


    
    //7. Update a message text identified by ID
    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<Integer> updateMessageById(@PathVariable ("message_id") long id, @RequestBody Message m){
        try{
            int indicator= messageservice.updateMessageById(id, m.getMessageText());
            return new ResponseEntity<>(indicator, HttpStatus.OK);
        }catch(InvalidException i){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

    //8. Retrieve all messages by an account/user 
    @GetMapping("/accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getMessagesByPostedBy(@PathVariable ("account_id") long id){
        return new ResponseEntity<>(messageservice.getMessagesByUser(id), HttpStatus.OK);

    }
    

}
