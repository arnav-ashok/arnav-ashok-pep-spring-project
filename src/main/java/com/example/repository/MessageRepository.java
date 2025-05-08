package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

import com.example.entity.Message;


public interface MessageRepository extends JpaRepository<Message, Long>{
    //3. Process the creation of new messages
    //Using the save method here
    
    //4. Retrieve all messages
    //Using the find all method here

    //5. Retrieve a message by ID
    Message findMessageById(long id);
    
    
    //6. Delete a message identified by ID
    
    
    
    //7. Update a message text identified by ID

    
    
    //8. Retrieve all messages by an account/user 
    List<Message> findMessagesByPostedBy(long postedBy);

    
}
