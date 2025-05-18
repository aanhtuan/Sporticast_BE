package com.example.besporticast.DTO.Request;

import com.example.besporticast.Entity.Audiobook;
import lombok.Data;

@Data
public class AudiobookDTO {
    private int id;
    private String title;
    private String author;
    private Integer duration;
    private Float rating;
    private Integer listenCount;

    // Constructor nhận entity Audiobook để map dữ liệu
    public AudiobookDTO(Audiobook audiobook) {
        this.id = audiobook.getId();
        this.title = audiobook.getTitle();
        this.author = audiobook.getAuthor();
        this.duration = audiobook.getDuration();
        this.rating = audiobook.getRating();
        this.listenCount = audiobook.getListenCount();
    }

    // Constructor không tham số (lombok @Data có thể tạo nhưng bạn nên khai báo rõ)
}
