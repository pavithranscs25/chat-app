package com.chatapp.chatappbackend.auth;

import com.chatapp.chatappbackend.entity.User;
import com.chatapp.chatappbackend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User registerUser(User user){
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            return null;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public User loginUser(String email, String password) {

        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return null;
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return null;
        }

        return user;
    }
}
