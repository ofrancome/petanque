package com.ofrancome.petanque.app.controller;

import com.ofrancome.petanque.app.dto.GameDto;
import com.ofrancome.petanque.domain.games.Game;
import com.ofrancome.petanque.domain.games.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<Set<Game>> getGames() {
        return ResponseEntity.ok(gameService.retrieveGames());
    }

    @PostMapping
    public ResponseEntity<Game> addGame(@RequestBody GameDto gameDto) {
        return ResponseEntity.ok(gameService.addGame(gameDto.getWinners(), gameDto.getLosers(), gameDto.getLosersScore()));
    }
}
