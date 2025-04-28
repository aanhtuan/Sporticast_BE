package com.example.besporticast.Repository;

import com.example.besporticast.Entity.Audiobook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudiobookRepository extends JpaRepository<Audiobook, String> {
}
