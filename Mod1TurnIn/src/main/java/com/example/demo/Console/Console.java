package com.example.demo.Console;


import com.example.demo.Player.Player;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;


@Entity


@Table
public class Console {
    @Id
    @SequenceGenerator(
            name = "console_sequence",
            sequenceName = "console_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "console_sequence"
    )
    private Long serialNum;

    @ManyToMany
    @JoinTable(
            name = "ConsoleOwner",
            joinColumns = @JoinColumn(name = "console_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private Set<Player> consoleOwner = new HashSet<>();

    private String name;
    private LocalDate releaseDate;

    @Transient
    private Integer age;

    public Console() {
    }

    public Console(Long serialNum, String name, LocalDate releaseDate) {
        this.serialNum = serialNum;
        this.name = name;
        this.releaseDate = releaseDate;
        this.consoleOwner = null; // Initialize to null or set an initial owner if applicable
    }

    public Console(String name, LocalDate releaseDate) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.consoleOwner = null; // Initialize to null or set an initial owner if applicable
    }

    public Long getSerialNum() {
        return serialNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getAge() {
        return Period.between(this.releaseDate, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Player getConsoleOwner() {
        return (Player) consoleOwner;
    }

    public void setConsoleOwner(Player consoleOwner) {
        this.consoleOwner = (Set<Player>) consoleOwner;
    }

    @Override
    public String toString() {
        return "Console{" +
                "serialNum=" + serialNum +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", age=" + age +
                ", consoleOwner=" + consoleOwner +
                '}';
    }

    public void addConsoleOwner(Player player) {
        this.consoleOwner = (Set<Player>) player;
    }
}