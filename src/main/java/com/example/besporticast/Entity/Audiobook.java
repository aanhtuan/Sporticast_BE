package com.example.besporticast.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "audiobooks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Audiobook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 64)
    private Integer id; // Kotlin dùng String cho ID

    @Size(max = 255)
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Size(max = 255)
    @NotNull
    @Column(name = "author", nullable = false)
    private String author;

    @NotNull
    @Column(name = "duration", nullable = false)
    private Integer duration; // Đổi từ Integer sang String theo Kotlin

    @Lob
    @Column(name = "image_url")
    private String imageUrl;

    @NotNull
    @Column(name = "rating", nullable = false)
    private Float rating;

    @NotNull
    @Column(name = "listen_count", nullable = false)
    private Integer listenCount;

    @Size(max = 255)
    @NotNull
    @Column(name = "category", nullable = false)
    private String category;
    @Column(name = "audio_url", nullable = false)
    private String audioUrl;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "language",nullable = false)
    private String language;
    // Audiobook.java
    @Column(name = "chapter_limit",nullable = false)
    private Integer chapterLimit=0;


}
