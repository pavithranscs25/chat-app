package com.chatapp.chatappbackend.auth;

import com.chatapp.chatappbackend.entity.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        User registeredUser = authService.registerUser(user);

        if (registeredUser == null) {
            return ResponseEntity.status(409).body("Email already exists");
        }
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        User loggedInUser = authService.loginUser(user.getEmail(), user.getPassword());

        if (loggedInUser == null) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }

        return ResponseEntity.ok(loggedInUser);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(
            HttpServletRequest request,
            Authentication authentication) {

        request.getSession().invalidate();

        return ResponseEntity.ok("Logout successful");
    }
}
