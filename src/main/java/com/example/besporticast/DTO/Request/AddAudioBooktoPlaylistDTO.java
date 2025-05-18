package com.example.besporticast.DTO.Request;

import lombok.Data;

@Data
public class AddAudioBooktoPlaylistDTO {
    private Integer playlistId;
    private String playlistName;
    private Integer audiobookId;

}
