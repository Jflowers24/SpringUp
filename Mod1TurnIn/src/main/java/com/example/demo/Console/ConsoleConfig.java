package com.example.demo.Console;

import com.example.demo.Game.Game;
import com.example.demo.Game.GameRepository;
import com.example.demo.Player.Player;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class ConsoleConfig {

    @Bean
    CommandLineRunner commandLineRunner1(
            ConsoleRepository repository){
        return args -> {
            Console Xbox = new Console(
                    "Xbox 360",
                    LocalDate.of(2005, NOVEMBER, 22)




            );
            Console PlayStation = new Console(
                   "PlayStation 3" ,
                    LocalDate.of(2006, NOVEMBER, 11)



            );
            repository.saveAll(List.of(Xbox, PlayStation));
        };
    }
}



