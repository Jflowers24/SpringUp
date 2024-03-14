package com.example.demo.Player;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("Player")
public class PlayerConfig {


    @Bean
    CommandLineRunner commandLineRunner3(
            PlayerRepository repository) {
        return args -> {

            Player Jerm = new Player(
                   "Jerm"
            );
            Player Kale = new Player(
                    "Kale"
            );
            Player Josh = new Player(
                    "Josh"
            );
            repository.saveAll(List.of(Jerm, Kale, Josh));

        };
    }

}
