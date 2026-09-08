package com.chatapp.chatappbackend.config;

import com.chatapp.chatappbackend.auth.GoogleOAuth2SuccessHandler;
import com.chatapp.chatappbackend.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public GoogleOAuth2SuccessHandler googleOAuth2SuccessHandler(UserService userService) {
        return new GoogleOAuth2SuccessHandler(userService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            GoogleOAuth2SuccessHandler googleOAuth2SuccessHandler) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .securityContext(context -> context.requireExplicitSave(false))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/auth/login",
                                "/auth/register",
                                "/oauth2/**",
                                "/login/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth -> oauth
                        .successHandler(googleOAuth2SuccessHandler)
                );

        return http.build();
    }
}