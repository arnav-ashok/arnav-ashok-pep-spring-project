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
    public Message persistMessage(Message message){
        if(message == null){
            throw new OtherException("Input a valid message object.");
        }

        Optional<Message> queriedMessagePostedBy = messageRepository.findById(message.getPostedBy());
        if(!queriedMessagePostedBy.isPresent()){
            throw new OtherException("Account does not exist");
        } 
        if(message.getMessageText() == null || message.getMessageText().length()>255||message.getMessageText().trim().isEmpty()){
            throw new OtherException("Message contents invalid.");
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
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if(optionalMessage.isPresent()){
            return optionalMessage.get();
        }else{
            return null;
        }
    }
    //6. Delete a message identified by ID

    public void deleteMessageById(long id){
        messageRepository.deleteById(id);
    }
   
    //7. Update a message text identified by ID
    public int updateMessageById(long id, String messagetext){
        Optional<Message> optionalMessage=messageRepository.findById(id);
        if(optionalMessage.isPresent()){
            Message message = optionalMessage.get();
            message.setMessageText(messagetext);
            messageRepository.save(message);
            return 1;
        }else{
            return 0;
        }

    }
        
    //8. Retrieve all messages by an account/user

}
