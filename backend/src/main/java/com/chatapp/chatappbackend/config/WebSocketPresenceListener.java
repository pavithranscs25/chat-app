package com.chatapp.chatappbackend.config;

import com.chatapp.chatappbackend.service.PresenceService;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketPresenceListener {

    private final PresenceService presenceService;

    public WebSocketPresenceListener(
            PresenceService presenceService) {
        this.presenceService = presenceService;
    }

    @EventListener
    public void handleSessionDisconnect(
            SessionDisconnectEvent event) {

        StompHeaderAccessor accessor =
                StompHeaderAccessor.wrap(event.getMessage());

        String sessionId =
                accessor.getSessionId();

        if (sessionId == null) {
            return;
        }

        presenceService.userDisconnected(sessionId);

        System.out.println(
                "USER WITH SESSION " +
                        sessionId +
                        " IS OFFLINE"
        );
    }
}