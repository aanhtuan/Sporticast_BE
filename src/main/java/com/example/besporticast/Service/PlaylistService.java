package com.example.besporticast.Service;

import com.example.besporticast.DTO.Request.AddAudioBooktoPlaylistDTO;
import com.example.besporticast.DTO.Request.AudiobookDTO;
import com.example.besporticast.DTO.Request.PlaylistDTO;
import com.example.besporticast.Entity.Audiobook;
import com.example.besporticast.Entity.Playlist;
import com.example.besporticast.Entity.PlaylistItem;
import com.example.besporticast.Repository.AudiobookRepository;
import com.example.besporticast.Repository.PlaylistItemRepository;
import com.example.besporticast.Repository.PlaylistRepository;
import com.liferay.portal.kernel.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private AudiobookRepository audiobookRepository;
    @Autowired
    private PlaylistItemRepository playlistItemRepository;

    @Transactional
    public void addAudiobookToPlaylist(AddAudioBooktoPlaylistDTO request) {
        Audiobook audiobook = audiobookRepository.findById(Long.valueOf(request.getAudiobookId()))
                .orElseThrow(() -> new RuntimeException("Audiobook not found"));
        Playlist playlist;

        if (request.getPlaylistId() != null) {
            playlist = playlistRepository.findById(request.getPlaylistId())
                    .orElseThrow(() -> new RuntimeException("Playlist not found"));
        } else {
            playlist = new Playlist();
            playlist.setName(request.getPlaylistName());
            playlist.setCreatedAt(Instant.now());
            playlist = playlistRepository.save(playlist);
        }


        Integer maxOrder = playlistItemRepository.findMaxOrderInPlaylist(playlist.getId());
        int newOrder = maxOrder + 1;

        PlaylistItem item = new PlaylistItem();
        item.setPlaylist(playlist);
        item.setAudiobook(audiobook);
        item.setOrder(newOrder);

        playlistItemRepository.save(item);
    }
    public List<AudiobookDTO> getAudiobooksInPlaylist(Integer playlistId) {
        List<PlaylistItem> items = playlistItemRepository.findByPlaylistIdOrderByOrderAsc(playlistId);
        return items.stream()
                .map(item -> new AudiobookDTO(item.getAudiobook()))
                .toList();
    }
    public List<PlaylistDTO> getAllPlaylists() {
        List<Playlist> playlists = playlistRepository.findAll();
        return playlists.stream()
                .map(PlaylistDTO::new)
                .toList();
    }

}
