package com.chatapp.chatappbackend.controller;

import com.chatapp.chatappbackend.entity.Message;
import com.chatapp.chatappbackend.repository.MessageRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageRepository messageRepository;

    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/{user1}/{user2}")
    public List<Message> getConversation(
            @PathVariable Integer user1,
            @PathVariable Integer user2) {

        return messageRepository
                .findBySenderIdAndReceiverIdOrSenderIdAndReceiverId(
                        user1, user2, user2, user1
                );
    }
}