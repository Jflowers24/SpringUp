package com.example.demo.Console;

import com.example.demo.Game.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsoleRepository
        extends JpaRepository<Console, Long>{

    @Query("select s FROM Console s WHERE s.name = ?1")
    Optional<Console> findConsoleByCname(String Name);
}

