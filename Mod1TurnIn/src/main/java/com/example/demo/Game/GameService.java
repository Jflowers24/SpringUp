package com.example.demo.Game;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GameService  {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> GetGames(){
        return gameRepository.findAll();
    }

    public void addNewGame(Game game) {
        Optional<Game> gameOptional = gameRepository
                .findGameByTitle(game.getTitle());
        if (gameOptional.isPresent()) {
            throw new IllegalStateException("Name Already Taken");
        }
        gameRepository.save(game);
        System.out.println(game);

    }

    public void deleteGame(Long gameserialNum){

        gameRepository.findById(gameserialNum);
         boolean exists = gameRepository.existsById(gameserialNum);
         if (!exists){
             throw new IllegalStateException("Game with Serial Number" + gameserialNum + " does not exist");

         }
         gameRepository.deleteById(gameserialNum);
    }

    public Optional<Game> SearchGame(Long gameserialNum){

        return Optional.ofNullable(gameRepository.findById(gameserialNum).orElseThrow(() -> new IllegalStateException("Game doesn't exist")));
    }

    @Transactional
    public void updateStudent(Long gameserialNum,
                              String Title,
                              String Genre) {
        Game game = gameRepository.findById(gameserialNum)
                .orElseThrow(() -> new IllegalStateException(
                        "Game with id" + gameserialNum + " does not exist"));

        if (Title != null &&
                Title.length() > 0 &&
                !Objects.equals(game.getTitle(), Title)) {
            game.setTitle(Title);
        }
        if (Genre != null &&
                !Objects.equals(game.getGenre(), Genre)) {
            Optional<Game> gameOptional = gameRepository
                    .findGameByTitle(Title);
            if (gameOptional.isPresent()) {
                throw new IllegalStateException("Genre Is Too Close to another it must be a clone game boooooo.") {

                };
            }

            game.setGenre(Genre);
        }
    }

    public Game save(Game game) {
        return game;
    }
}
