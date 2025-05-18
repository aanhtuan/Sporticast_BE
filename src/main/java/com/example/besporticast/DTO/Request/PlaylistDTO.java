package com.example.besporticast.DTO.Request;

import com.example.besporticast.Entity.Playlist;
import lombok.Data;

import java.time.Instant;
@Data
public class PlaylistDTO {
    private Integer id;
    private String name;
    private Instant createdAt;

    public PlaylistDTO(Playlist playlist) {
        this.id = playlist.getId();
        this.name = playlist.getName();
        this.createdAt = playlist.getCreatedAt();
    }
}
