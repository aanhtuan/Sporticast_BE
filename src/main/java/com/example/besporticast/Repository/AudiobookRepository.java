package com.example.besporticast.Repository;

import com.example.besporticast.Entity.Audiobook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;

@Repository
public interface AudiobookRepository extends JpaRepository<Audiobook, String> {
    List <Audiobook> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author);

}
