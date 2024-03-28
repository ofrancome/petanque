package com.ofrancome.petanque.app.controller;

import com.ofrancome.petanque.domain.seasons.Season;
import com.ofrancome.petanque.domain.seasons.SeasonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("seasons")
public class SeasonController {

    private final SeasonService seasonService;

    public SeasonController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @PostMapping
    public ResponseEntity<Season> newSeason() {
        return ResponseEntity.ok(seasonService.newSeason());
    }

    @GetMapping
    public ResponseEntity<Set<Season>> getSeasons() {
        return ResponseEntity.ok(seasonService.retrieveSeasons());
    }

    @GetMapping("/current")
    public ResponseEntity<Season> currentSeason() {
        return ResponseEntity.ok(seasonService.currentSeason());
    }
}
