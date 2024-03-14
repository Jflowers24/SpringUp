package com.example.demo.Game;

import com.example.demo.Player.Player;
import com.example.demo.Player.PlayerRepository;
import com.example.demo.Player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/game")
public class GameController {
    private final GameService gameService;
    private final GameRepository gameRepository;

    @Autowired
    public GameController(GameService gameService, GameRepository gameRepository) {
        this.gameService = gameService;
        this.gameRepository = gameRepository;
    }

    @Autowired
    PlayerRepository playerRepository;
    @GetMapping
    public List<Game> GetGames(){
        return gameService.GetGames();

    }

    @GetMapping(path = "{gameserialNum}")
    public Optional<Game> SearchGame(@PathVariable("gameserialNum") Long gameserialNum){
        return gameService.SearchGame(gameserialNum);
    }

    @PostMapping
    public void registerNewGame(@RequestBody Game game){
        gameService.addNewGame(game);
    }
    @DeleteMapping(path = "{gameserialNum}")
    public void deleteGame(@PathVariable(
            "gameserialNum") Long gameserialNum){
        gameService.deleteGame(gameserialNum);
    }
    @PutMapping(path = "{gameserialNum}")
    public void UpdateGame(
            @PathVariable("gameserialNum") Long gameserialNum,
            @RequestParam(required = false) String Title,
            @RequestParam(required = false) String Genre) {
        gameService.updateStudent(gameserialNum, Title, Genre);

}
    @PutMapping("{gameserialNum}/addOwner/{playerId}")
    Game addGameToLibrary(
            @PathVariable Long gameserialNum,
            @PathVariable Long playerId
    ) {
        Game game = gameService.SearchGame(gameserialNum).orElse(null);
        Optional<Player> player = playerRepository.findById(playerId);

        if (game != null && player.isPresent()) {
            game.setGameOwner(player.get());
            return gameService.save(game);
        }

        return null;
    }
}

