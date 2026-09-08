package com.chatapp.chatappbackend.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import com.chatapp.chatappbackend.service.UserService;
import com.chatapp.chatappbackend.entity.User;

public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler{

    private final UserService userService;

    public GoogleOAuth2SuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        OAuth2User oauth2User = ((OAuth2AuthenticationToken) authentication).getPrincipal();

        String name = oauth2User.getAttribute("name");
        String email = oauth2User.getAttribute("email");

        User user = userService.getUserByEmail(email);

        if (user == null) {
            user = new User();
            user.setUsername(name);
            user.setEmail(email);
            user.setPassword("GOOGLE_USER");
            userService.createUser(user);
        }

        response.sendRedirect("http://localhost:5173/chat");
    }
}
