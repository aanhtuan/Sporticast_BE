package com.example.besporticast.Controller;

import com.example.besporticast.DTO.Request.LoginRequest;
import com.example.besporticast.DTO.Request.RegisterRequest;


import com.example.besporticast.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody RegisterRequest request) {
        String result = userService.registerUser(request.name, request.email, request.password);
        Map<String, String> response = new HashMap<>();
        response.put("message", result);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginRequest request) {
        boolean success = userService.loginUser(request.email, request.password);
        Map<String, String> response = new HashMap<>();
        response.put("message", success ? "Login Success" : "Login Failed");
        return ResponseEntity.ok(response);

    }
}
