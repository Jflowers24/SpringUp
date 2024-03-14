package com.example.demo.Game;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.DECEMBER;

@Configuration
public class GameConfig {

    @Bean
    CommandLineRunner commandLineRunner2(
            GameRepository repository) {
        return args -> {

             Game Pokemon = new Game(

                    "Pokemon",
                    LocalDate.of(2019, DECEMBER, 5),
                     "RPG"



            );
            Game Zelda = new Game(

                    "Zelda",
                    LocalDate.of(2017, DECEMBER, 5),
                    "RPG"
            );
            repository.saveAll(
                    List.of(Zelda, Pokemon)
            );
        };

    }
}
