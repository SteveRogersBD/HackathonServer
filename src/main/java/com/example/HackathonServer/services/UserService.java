package com.example.HackathonServer.services;

import com.example.HackathonServer.jwtFIles.JWTUtil;
import com.example.HackathonServer.models.User;
import com.example.HackathonServer.repositories.UserRepo;
import com.example.HackathonServer.requests.LogInRequest;
import com.example.HackathonServer.requests.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    JWTUtil jwtUtil;
    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;

    public User saveNewUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public User getById(Long id)
    {
        User user = userRepo.findById(id).orElseThrow(
                ()->new UsernameNotFoundException("User not found with id "+id));
        return user;
    }
    public boolean verify(String username, String password)
    {
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken
                        (username, password));
        return authentication.isAuthenticated();

    }

    public boolean verify(RegisterRequest request)
    {
        String email = request.getEmail();
        String password = request.getPassword();
        String username = request.getUsername();
        if(userRepo.existsByEmail(email)) return false;
        if(userRepo.existsByUsername(username)) return false;

        return true;

    }

    public String login(LogInRequest request) {
        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + request.getEmail()));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.createJWTFromUsername(user.getUsername());
    }

    public int numberOfChildren(Long id)
    {
        User user = getById(id);
        return user.getChildren().size();
    }



}
