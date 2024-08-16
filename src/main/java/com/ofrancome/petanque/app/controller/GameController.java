package com.ofrancome.petanque.app.controller;

import com.ofrancome.petanque.app.dto.requests.GameCreationDto;
import com.ofrancome.petanque.app.dto.responses.GameDto;
import com.ofrancome.petanque.domain.games.Game;
import com.ofrancome.petanque.domain.api.GameService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<GameDto>> getGames() {
        return ResponseEntity.ok(gameService.retrieveGames().stream().map(GameDto::from).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<GameDto> addGame(@Valid @RequestBody GameCreationDto gameCreationDto) {
        Game game = gameService.addGame(gameCreationDto.getWinners(), gameCreationDto.getLosers(), gameCreationDto.getLosersScore());
        return ResponseEntity.ok(GameDto.from(game));
    }
}
