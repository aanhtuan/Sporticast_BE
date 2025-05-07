package com.example.besporticast.Controller;

import com.example.besporticast.DTO.Request.LoginRequest;
import com.example.besporticast.DTO.Request.RegisterRequest;
import com.example.besporticast.Service.UserService;
import com.example.besporticast.Util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody RegisterRequest request) {
        String result = userService.registerUser(request.name, request.email, request.password);
        Map<String, String> response = new HashMap<>();
        response.put("message", result);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> loginData = userService.loginUser(request.email, request.password);

        if (loginData != null) {
            response.put("message", "Login Success");
            response.put("token", loginData.get("token"));
            response.put("is_admin", loginData.get("is_admin"));
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Login Failed");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }


}
