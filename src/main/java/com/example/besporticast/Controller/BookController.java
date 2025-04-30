package com.example.besporticast.Controller;

import com.example.besporticast.Entity.Audiobook;
import com.example.besporticast.Service.AudioBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class BookController {
    @Autowired
    private AudioBookService audioBookService;

    @GetMapping("/featuredbooks")
    public List<Audiobook> getAllAudiobook() {
        return audioBookService.getAllAudiobooks();
    }
}
