package com.ofrancome.petanque.app.controller;

import com.ofrancome.petanque.domain.seasons.Season;
import com.ofrancome.petanque.domain.seasons.SeasonService;
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
    public Season newSeason() {
        return seasonService.newSeason();
    }

    @GetMapping
    public Set<Season> getSeasons() {
        return seasonService.retrieveSeasons();
    }

    @GetMapping("/current")
    public Season currentSeason() {
        return seasonService.currentSeason();
    }
}
