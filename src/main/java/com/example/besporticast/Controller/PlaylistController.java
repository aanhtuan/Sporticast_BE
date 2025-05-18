package com.example.besporticast.Controller;

import com.example.besporticast.DTO.Request.AddAudioBooktoPlaylistDTO;
import com.example.besporticast.DTO.Request.AudiobookDTO;
import com.example.besporticast.DTO.Request.PlaylistDTO;
import com.example.besporticast.Entity.Audiobook;
import com.example.besporticast.Service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class PlaylistController {
    @Autowired
    PlaylistService playlistService;
    @PostMapping("/playlist")
    public ResponseEntity<?> addAudiobook(@RequestBody AddAudioBooktoPlaylistDTO request) {
        playlistService.addAudiobookToPlaylist(request);
        return ResponseEntity.ok("Audiobook added successfully");
    }
    @GetMapping("/playlist/{playlistId}/audiobooks")
    public ResponseEntity<?> getAudiobooksInPlaylist(@PathVariable Integer playlistId) {
        List<AudiobookDTO> audiobooks = playlistService.getAudiobooksInPlaylist(playlistId);
        return ResponseEntity.ok(audiobooks);
    }
    @GetMapping("/playlists")
    public ResponseEntity<List<PlaylistDTO>> getAllPlaylists() {
        return ResponseEntity.ok(playlistService.getAllPlaylists());
    }



}
