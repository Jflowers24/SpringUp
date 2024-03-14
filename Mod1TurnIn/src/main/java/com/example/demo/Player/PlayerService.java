package com.example.demo.Player;

import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository){ this.playerRepository = playerRepository;}

    public List<Player> GetPlayers() { return playerRepository.findAll();}

    public void addNewPlayer(Player player) {
        Optional<Player> playerOptional = playerRepository
                .findPlayerByName(player.getName());
        if (playerOptional.isPresent()) {
            throw new IllegalStateException("Name Already Taken.");

        }
        playerRepository.save(player);
        System.out.println(player);


    }
    public void deletePlayer(Long playerId){
        playerRepository.findById(playerId);
        boolean exists = playerRepository.existsById(playerId);
        if (!exists){
            throw new IllegalStateException("Player with id " + playerId + " already Exists");

        }
        playerRepository.deleteById(playerId);
    }
    public Optional<Player> SearchPlayer(Long playerId){
        return Optional.ofNullable(playerRepository.findById(playerId).orElseThrow(() -> new IllegalStateException("Player Not Found")));
    }

    @Transactional
    public void updatePlayer(Long playerId,
                             String Name){
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new IllegalStateException(
                        "Player with id " + playerId + " doesn't exist"));
        if (Name != null &&
            Name.length() > 0 &&
                !Objects.equals(player.getName(), Name)){
            player.setName(Name);

        }

    }




}
