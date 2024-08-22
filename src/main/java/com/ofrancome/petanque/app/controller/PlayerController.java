package com.ofrancome.petanque.app.controller;

import com.ofrancome.petanque.app.dto.requests.PlayerCreationDto;
import com.ofrancome.petanque.app.dto.responses.GameDto;
import com.ofrancome.petanque.app.dto.responses.PlayerDto;
import com.ofrancome.petanque.domain.api.PlayerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<List<PlayerDto>> getAllPlayers() {
        return ResponseEntity.ok(playerService.retrievePlayers().stream().map(PlayerDto::from).toList());
    }

    @GetMapping("/{name}")
    public ResponseEntity<PlayerDto> getPlayer(@PathVariable("name") String name) {
        Optional<PlayerDto> playerDto = playerService.getPlayer(name).map(PlayerDto::from);
        return ResponseEntity.of(playerDto);
    }

    @PostMapping
    public ResponseEntity<PlayerDto> addPlayer(@Valid @RequestBody PlayerCreationDto playerCreationDto) {
        return ResponseEntity.ok(PlayerDto.from(playerService.addPlayer(playerCreationDto.getName())));
    }

    @GetMapping("/{name}/games")
    public ResponseEntity<List<GameDto>> getAllGamesForPlayer(@PathVariable("name") String name) {
        return playerService.getAllGamesFor(name).map(games ->
                        ResponseEntity.ok(games.stream().map(GameDto::from).sorted(Comparator.comparing(GameDto::gameNumber).reversed()).toList()))
                            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
