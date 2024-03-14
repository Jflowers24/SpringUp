package com.example.demo.Game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository
        extends JpaRepository<Game, Long> {

    @Query("SELECT s FROM Game s WHERE s.title = ?1")
    Optional<Game> findGameByTitle(String Title);
}
