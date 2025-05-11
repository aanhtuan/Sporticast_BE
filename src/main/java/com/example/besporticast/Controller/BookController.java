package com.example.besporticast.Controller;

import com.example.besporticast.Entity.Audiobook;
import com.example.besporticast.Service.AdminSvice.Ad_AddFavouriteService;
import com.example.besporticast.Service.AudioBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class BookController {
    @Autowired
    private AudioBookService audioBookService;

    @Autowired
    Ad_AddFavouriteService ad_AddFavouriteService;
    @GetMapping("/featuredbooks")
    public List<Audiobook> getAllAudiobook() {
        return audioBookService.getAllAudiobooks();
    }

    @PostMapping("/{userId}/favorites/{bookId}")
    public ResponseEntity<String> addFavourite(@PathVariable Long userId,
                                               @PathVariable Long bookId) {
        ad_AddFavouriteService.addFavourite(userId, bookId);
        return ResponseEntity.ok("Book added to Favourites");
    }

    @GetMapping("/{userId}/favorites")
    public ResponseEntity<Set<Audiobook>> getFavoriteBooks(@PathVariable Long userId) {
        Set<Audiobook> favorites = ad_AddFavouriteService.getFavoriteBooks(userId);
        return ResponseEntity.ok(favorites);
    }
}
