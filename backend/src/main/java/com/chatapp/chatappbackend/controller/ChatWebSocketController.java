package com.chatapp.chatappbackend.controller;

import com.chatapp.chatappbackend.entity.ChatMessage;
import com.chatapp.chatappbackend.entity.Message;
import com.chatapp.chatappbackend.entity.PresenceMessage;
import com.chatapp.chatappbackend.repository.MessageRepository;
import com.chatapp.chatappbackend.service.PresenceService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class ChatWebSocketController {

    private final MessageRepository messageRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final PresenceService presenceService;

    public ChatWebSocketController(
            MessageRepository messageRepository,
            SimpMessagingTemplate messagingTemplate,
            PresenceService presenceService) {

        this.messageRepository = messageRepository;
        this.messagingTemplate = messagingTemplate;
        this.presenceService = presenceService;
    }

    // ---------------- SEND MESSAGE ----------------

    @MessageMapping("/send")
    public void sendMessage(ChatMessage chatMessage) {

        System.out.println("WEBSOCKET MESSAGE RECEIVED");
        System.out.println(
                "Sender: " + chatMessage.getSenderId()
        );
        System.out.println(
                "Receiver: " + chatMessage.getReceiverId()
        );
        System.out.println(
                "Content: " + chatMessage.getContent()
        );

        // Save message
        Message message = new Message(
                chatMessage.getSenderId(),
                chatMessage.getReceiverId(),
                chatMessage.getContent(),
                LocalDateTime.now().toString()
        );

        messageRepository.save(message);

        // Send only to receiver
        String destination =
                "/topic/messages/" +
                        chatMessage.getReceiverId();

        System.out.println(
                "Sending WebSocket message to: " +
                        destination
        );

        messagingTemplate.convertAndSend(
                destination,
                chatMessage
        );

        System.out.println(
                "WebSocket message sent successfully"
        );
    }

    // ---------------- USER PRESENCE ----------------

    @MessageMapping("/presence")
    public void updatePresence(
            PresenceMessage presenceMessage,
            StompHeaderAccessor accessor) {

        String sessionId =
                accessor.getSessionId();

        if (sessionId == null) {
            return;
        }

        presenceService.userConnected(
                presenceMessage.getUserId(),
                sessionId
        );

        System.out.println(
                "Presence updated for user: " +
                        presenceMessage.getUserId()
        );
    }
}
