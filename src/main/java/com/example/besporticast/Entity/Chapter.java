package com.example.besporticast.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Setter
@Getter
@Entity
@Table(name = "chapters")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "audiobook_id", nullable = false)
    private Audiobook audiobook;

    @Size(max = 255)
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Lob
    @Column(name = "audio_url", nullable = false)
    private String audioUrl;

    @NotNull
    @Column(name = "duration", nullable = false)
    private Integer duration;

    @NotNull
    @Column(name = "`order`", nullable = false)
    private Integer order;

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

}