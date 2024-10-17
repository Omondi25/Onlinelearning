package com.example.controllers;


import com.example.auth.JwtAuth;
import com.example.models.User;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

    @RestController
    @RequestMapping("/auth")
    public class AuthenticationController {

        @Autowired
        private AuthenticationManager authenticationManager;


        @Autowired
        private UserRepository userRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @PostMapping("/register")
        public String register(@RequestBody User user) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return "User registered successfully";
        }

        @PostMapping("/login")
        public String login(@RequestBody User user) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            JwtAuth jwtUtil = null;
            return jwtUtil.generateToken(user.getUsername());
        }
    }

}
