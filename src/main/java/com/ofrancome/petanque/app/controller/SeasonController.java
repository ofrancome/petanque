package com.ofrancome.petanque.app.controller;

import com.ofrancome.petanque.app.dto.responses.SeasonDto;
import com.ofrancome.petanque.domain.seasons.Season;
import com.ofrancome.petanque.domain.seasons.SeasonService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("seasons")
public class SeasonController {

    private final SeasonService seasonService;

    public SeasonController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @PostMapping
    @Operation(summary ="End the current season and create the next one")
    public ResponseEntity<SeasonDto> newSeason() {
        return ResponseEntity.ok(SeasonDto.from(seasonService.newSeason()));
    }

    @GetMapping
    @Operation(summary ="Retrieve all seasons")
    public ResponseEntity<List<SeasonDto>> getSeasons() {
        return ResponseEntity.ok(seasonService.retrieveSeasons().stream().map(SeasonDto::from).toList());
    }

    @GetMapping("/current")
    @Operation(summary = "Retrieve current version")
    public ResponseEntity<SeasonDto> currentSeason() {
        return ResponseEntity.ok(SeasonDto.from(seasonService.currentSeason()));
    }
}
