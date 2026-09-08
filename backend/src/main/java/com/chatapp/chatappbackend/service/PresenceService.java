package com.chatapp.chatappbackend.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PresenceService {

    // userId -> WebSocket sessionId
    private final Map<Integer, String> onlineUsers =
            new ConcurrentHashMap<>();

    private final SimpMessagingTemplate messagingTemplate;

    public PresenceService(
            SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // User becomes online
    public void userConnected(
            Integer userId,
            String sessionId) {

        onlineUsers.put(userId, sessionId);

        System.out.println(
                "USER " + userId + " IS ONLINE"
        );

        sendPresenceUpdate();
    }

    // User becomes offline
    public void userDisconnected(
            String sessionId) {

        onlineUsers.entrySet().removeIf(
                entry ->
                        entry.getValue().equals(sessionId)
        );

        System.out.println(
                "USER WITH SESSION " +
                        sessionId +
                        " IS OFFLINE"
        );

        sendPresenceUpdate();
    }

    // Send current online users to all clients
    private void sendPresenceUpdate() {

        messagingTemplate.convertAndSend(
                "/topic/presence",
                new ArrayList<>(onlineUsers.keySet())
        );
    }

    public boolean isUserOnline(Integer userId) {
        return onlineUsers.containsKey(userId);
    }
}