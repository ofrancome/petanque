package com.ofrancome.petanque.app.controller;

import com.ofrancome.petanque.domain.players.Player;
import com.ofrancome.petanque.domain.players.PlayerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public Set<Player> getAllPlayers() {
        return playerService.retrievePlayers();
    }

    @PostMapping("/{name}")
    public Player addPlayer(@PathVariable String name) {
        return playerService.addPlayer(name);
    }
}
