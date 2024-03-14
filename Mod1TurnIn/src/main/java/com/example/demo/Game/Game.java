package com.example.demo.Game;

import com.example.demo.Player.Player;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Game {
    @Id
    @SequenceGenerator(
            name = "game_sequence",
            sequenceName = "game_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "game_sequence"
    )
    private Long serialNum;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player gameOwner;

    private String title;
    private LocalDate releaseDate;

    @Transient
    private Integer age;
    private String genre;

    public Game() {
    }

    public Game(Long serialNum, String title, LocalDate releaseDate, String genre) {
        this.serialNum = serialNum;
        this.title = title;
        this.releaseDate = releaseDate;
        this.genre = genre;
    }

    public Game(String title, LocalDate releaseDate, String genre) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.genre = genre;
    }

    public Long getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(Long serialNum) {
        this.serialNum = serialNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        this.age = calculateAge();
    }

    public Integer getAge() {
        return age;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Game{" +
                "serialNum=" + serialNum +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", age=" + age +
                ", genre='" + genre + '\'' +
                '}';
    }

    public Player getGameOwner() {
        return gameOwner;
    }

    public void setGameOwner(Player gameOwner) {
        this.gameOwner = gameOwner;
    }

    private Integer calculateAge() {
        return Period.between(this.releaseDate, LocalDate.now()).getYears();
    }
}