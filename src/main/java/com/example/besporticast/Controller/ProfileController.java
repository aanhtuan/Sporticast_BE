package com.example.besporticast.Controller;

import com.example.besporticast.DTO.Request.UserDto;
import com.example.besporticast.Entity.User;
import com.example.besporticast.Repository.UserRepository;
import com.example.besporticast.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class ProfileController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
       return userService.getUserById(id);
    }
}
