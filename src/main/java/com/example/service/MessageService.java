package com.example.service;

import com.example.repository.MessageRepository;
import com.example.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.exception.UnauthorizedException;
import com.example.exception.UsernameAlreadyExistsException;
import com.example.exception.InvalidException;
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
    public Message persistMessage(Message message){
        if(message == null){
            throw new InvalidException("Input a valid message object.");
        }

        Optional<Message> queriedMessagePostedBy = messageRepository.findById((long)message.getPostedBy());
        if(!queriedMessagePostedBy.isPresent()){
            throw new InvalidException("Account does not exist");
        } 
        if(message.getMessageText() == null || message.getMessageText().length()>255||message.getMessageText().trim().isEmpty()){
            throw new InvalidException("Message contents invalid.");
        }
        return messageRepository.save(message);
        
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
        if(retrievedMessage.isPresent()){
            Message message = retrievedMessage.get();
            message.setMessageText(messagetext);
            messageRepository.save(message);
            return 1;
        }
        return 0;
    }
        
    //8. Retrieve all messages by an account/user
    public List<Message> getMessagesByUser(long postedBy){
        return messageRepository.findMessagesByPostedBy(postedBy);

    }

}
