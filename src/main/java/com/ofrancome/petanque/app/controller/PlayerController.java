package com.ofrancome.petanque.app.controller;

import com.ofrancome.petanque.app.dto.requests.PlayerCreationDto;
import com.ofrancome.petanque.app.dto.responses.PlayerDto;
import com.ofrancome.petanque.domain.players.Player;
import com.ofrancome.petanque.domain.players.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    @Operation(summary = "Retrieve all players")
    public ResponseEntity<List<PlayerDto>> getAllPlayers() {
        return ResponseEntity.ok(playerService.retrievePlayers().stream().map(PlayerDto::from).collect(Collectors.toList()));
    }

    @GetMapping("/{name}")
    @Operation(summary = "Retrieve given player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Player not found")
    })
    public ResponseEntity<PlayerDto> getPlayer(@PathVariable("name") String name) {
        Optional<PlayerDto> playerDto = playerService.getPlayer(name).map(PlayerDto::from);
        return ResponseEntity.of(playerDto);
    }

    @PostMapping
    @Operation(summary = "Sign up a new player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid Player")
    })
    public ResponseEntity<PlayerDto> addPlayer(@Valid @RequestBody PlayerCreationDto playerCreationDto) {
        return ResponseEntity.ok(PlayerDto.from(playerService.addPlayer(playerCreationDto.getName())));
    }
}
