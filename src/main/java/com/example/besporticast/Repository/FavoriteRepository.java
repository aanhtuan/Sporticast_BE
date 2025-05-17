package com.example.besporticast.Repository;

import com.example.besporticast.Entity.Favorite;
import com.example.besporticast.Entity.FavoriteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Favorite f WHERE f.audiobook.id = :audiobookId")
    void deleteByAudiobookId(Long audiobookId);
}
