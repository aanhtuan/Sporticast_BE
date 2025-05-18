package com.example.besporticast.Repository;

import com.example.besporticast.Entity.PlaylistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaylistItemRepository extends JpaRepository<PlaylistItem, Integer> {
    @Query("SELECT COALESCE(MAX(p.order), 0) FROM PlaylistItem p WHERE p.playlist.id = :playlistId")
    Integer findMaxOrderInPlaylist(@Param("playlistId") Integer playlistId);
    List<PlaylistItem> findByPlaylistIdOrderByOrderAsc(Integer playlistId);
}