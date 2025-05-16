package com.example.besporticast.Entity;

import jakarta.persistence.*;
import com.example.besporticast.Entity.Audiobook;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @Lob
    @Column(name = "avatar")
    private String avatar;

    @NotNull
    @Column(name = "is_admin", nullable = false)
    private Boolean is_admin = false;

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "is_verified")
    private boolean isVerified = false;

    @ManyToMany
    private Set<Audiobook> favourites = new HashSet<>();

    public Set<Audiobook> getFavourites() {
        return favourites;
    }




    public void setFavourites(Set<Audiobook> favourites) {
        this.favourites = favourites;
    }


}