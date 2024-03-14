package com.example.demo.Player;


import com.example.demo.Console.Console;
import com.example.demo.Game.Game;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import static com.sun.tools.javac.resources.CompilerProperties.Fragments.Local;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@Entity
@Table
public class Player {
    @Id
    @SequenceGenerator(
            name = "player_sequence",
            sequenceName =  "player_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "player_sequence"
    )
    private Long Id;
    private String Name;

    @OneToMany(mappedBy = "gameOwner")
    private Set<Game> games = new HashSet<>();

    @ManyToMany(mappedBy = "consoleOwner")
    private Set<Console> consoles = new HashSet<>();





    public Player() {
    }

    public Player(Long id,
                String name

                ) {
        Id = id;
        Name = name;
    }

    public Player(String name
                ) {
        Name = name;
    }

    public Long getId() {
        return Id;
    }

    public void setSerialNum(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Set<Game> getGame() {
        return games;
    }







    @Override
    public String toString() {
        return "Player{" +
                "Id=" + Id +
                ", Name='" + Name + '\''
                ;
    }
}