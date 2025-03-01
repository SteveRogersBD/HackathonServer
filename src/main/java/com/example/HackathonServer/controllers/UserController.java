package com.example.HackathonServer.controllers;


import com.example.HackathonServer.UserUtil;
import com.example.HackathonServer.jwtFIles.JWTUtil;
import com.example.HackathonServer.models.User;
import com.example.HackathonServer.reponses.ApiResponse;
import com.example.HackathonServer.reponses.UserResponse;
import com.example.HackathonServer.repositories.UserRepo;
import com.example.HackathonServer.requests.LogInRequest;
import com.example.HackathonServer.requests.RegisterRequest;
import com.example.HackathonServer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/public/register")
    public ApiResponse<UserResponse> registerUser(@RequestBody RegisterRequest request)
    {
        if(userService.verify(request))
        {
            User user = new User();
            user.setUsername(request.getUsername());
            user.setPassword(request.getPassword());
            user.setEmail(request.getEmail());
            user.setRole(request.getRole());
            User savedUser = userService.saveNewUser(user);
            return ApiResponse.onSuccess("User registered!!!",
                    UserUtil.transformToResponse(savedUser));
        }
        return null;
    }

    @PostMapping("/public/log-in")
    public ApiResponse<String>logInUser(LogInRequest req)
    {
        String token = null;
        token = userService.login(req);
        return ApiResponse.onSuccess("Successfully logged in", token);
    }

    @GetMapping("/children/user/{userId}")
    public ApiResponse<Integer>getNumberOfChildren(@PathVariable Long userId)
    {
        int children = userService.numberOfChildren(userId);
        return ApiResponse.onSuccess("Retrieved Successfully!!!", children);
    }



}
