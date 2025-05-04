package com.example.besporticast.Controller;

import com.example.besporticast.Entity.Audiobook;
import com.example.besporticast.Service.AudioBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class SearchController {
    @Autowired
    AudioBookService audioBookService;
    @GetMapping("/search")
    public ResponseEntity<List<Audiobook>> searchBooks(@RequestParam("query")String query) {
        List<Audiobook> results = audioBookService.searchBooks(query);
        return ResponseEntity.ok(results);



    }

}







