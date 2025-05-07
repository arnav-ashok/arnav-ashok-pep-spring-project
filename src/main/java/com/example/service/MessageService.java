package com.example.service;

import com.example.repository.MessageRepository;
import com.example.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    MessageRepository messageRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }
    //3. Process the creation of new messages
    
    
    //4. Retrieve all messages
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }
    //5. Retrieve a message by ID
    public Message getMessageById(long id){
        //findById returns a type Optional<>. This helps the developer avoid null pointer 
        // exceptions. We can use the method .get() to convert an Optional<Message> to Message.
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if(optionalMessage.isPresent()){
            return optionalMessage.get();
        }else{
            return null;
        }
    }
    //6. Delete a message identified by ID

    public void deleteMessage(long id){
        messageRepository.deleteById(id);
    }
   
    //7. Update a message text identified by ID

    
    
    //8. Retrieve all messages by an account/user

}
