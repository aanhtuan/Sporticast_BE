package com.example.besporticast.Repository;

import com.example.besporticast.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    @Modifying
    @Query(value = "DELETE FROM users_favourites WHERE favourites_id = :bookId", nativeQuery = true)
    void removeAllFavouritesWithBookId(@Param("bookId") Long bookId);

}
