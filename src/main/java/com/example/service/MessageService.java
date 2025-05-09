package com.example.service;

import com.example.repository.MessageRepository;
import com.example.repository.AccountRepository;
import com.example.entity.Account;
import com.example.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.exception.InvalidException;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    MessageRepository messageRepository;
    AccountRepository accountRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository= accountRepository;
    }

    //3. Process the creation of new messages
    public Message persistMessage(Message m){
        if (m==null){
            throw new InvalidException("Null message");
        }
        Optional<Account> existingAccount = accountRepository.findById((long)m.getPostedBy());
        if(existingAccount.isEmpty()){
            throw new InvalidException("No existing account");
        }
        if(m.getMessageText()==null||m.getMessageText().length()>255||m.getMessageText().trim().isEmpty()){
            throw new InvalidException("Enter valid credentials");
        }
        return messageRepository.save(m);

        
    }
    
    //4. Retrieve all messages
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    //5. Retrieve a message by ID
    public Message getMessageById(long id){
        //findById returns a type Optional<>. This helps the developer avoid null pointer 
        // exceptions. We can use the method .get() to convert an Optional<Message> to Message.
        Optional<Message> retrievedMessage = messageRepository.findById(id);
        if(retrievedMessage.isPresent()){
            return retrievedMessage.get();
        }
        return null;

    }
    //6. Delete a message identified by ID

    public int deleteMessageById(long id){
        if(messageRepository.existsById(id)){
            messageRepository.deleteById(id);
            return 1;
        }
        return 0;
    }
   
    //7. Update a message text identified by ID
    public int updateMessageById(long id, String messagetext){
        if(messagetext == null || messagetext.trim().isEmpty() || messagetext.length() > 255) {
            throw new InvalidException("Message content invalid.");
        }
        Optional<Message> retrievedMessage=messageRepository.findById(id);
        if(retrievedMessage.isEmpty()){
            throw new InvalidException("Message not found.");
        }
        Message message = retrievedMessage.get();
        message.setMessageText(messagetext);
        messageRepository.save(message);
        return 1;
    }
        
    //8. Retrieve all messages by an account/user
    public List<Message> getMessagesByUser(long postedBy){
        return messageRepository.findMessagesByPostedBy(postedBy);

    }

}
