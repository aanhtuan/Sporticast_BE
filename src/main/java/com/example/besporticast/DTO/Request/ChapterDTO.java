package com.example.besporticast.DTO.Request;

import com.example.besporticast.Entity.Chapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChapterDTO {
    private Integer id;
    private String title;
    private String audioUrl;
    private Integer audiobook;
    private Integer duration;
    private Integer order;
    public ChapterDTO(Chapter chapter) {
        this.audiobook = chapter.getAudiobook().getId();
        this.id = chapter.getId();
        this.title = chapter.getTitle();
        this.audioUrl = chapter.getAudioUrl();
        this.duration = chapter.getDuration();
        this.order = chapter.getOrder();
    }

}
