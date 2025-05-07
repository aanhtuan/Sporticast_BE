package com.example.besporticast.Service;

import com.example.besporticast.Entity.User;
import com.example.besporticast.Repository.UserRepository;
import com.example.besporticast.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private JwtUtil jwtUtil;

    public String registerUser(String name, String email, String password) {
        if (userRepository.existsByEmail(email)) {
            return "Email Already Exists";
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());
        userRepository.save(user);
        return "User created successfully";

    }

    public Map<String, Object> loginUser(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                String token = jwtUtil.generateToken(user.getEmail());
                Map<String, Object> data = new HashMap<>();
                data.put("token", token);
                data.put("is_admin", user.getIs_admin()); // chú ý getter
                return data;
            }
        }
        return null;
    }

}


