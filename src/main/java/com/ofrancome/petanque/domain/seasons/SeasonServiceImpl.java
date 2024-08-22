package com.ofrancome.petanque.domain.seasons;

import com.ofrancome.petanque.domain.LocalDateService;
import com.ofrancome.petanque.domain.api.SeasonService;
import com.ofrancome.petanque.infra.SeasonRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class SeasonServiceImpl implements SeasonService {

    private final SeasonRepository seasonRepository;
    private final LocalDateService localDateService;

    public SeasonServiceImpl(SeasonRepository seasonRepository, LocalDateService localDateService) {
        this.seasonRepository = seasonRepository;
        this.localDateService = localDateService;
    }

    @Override
    public Season newSeason() {
        Optional<Season> currentSeason = seasonRepository.currentSeason();
        currentSeason.ifPresent(season -> season.setEnd(localDateService.today()));
        final Season newSeason = new Season();
        newSeason.setGames(Collections.emptySet());
        newSeason.setRankings(Collections.emptySet());
        newSeason.setStart(localDateService.today());
        return seasonRepository.save(newSeason);
    }

    @Override
    public Set<Season> retrieveSeasons() {
        Iterable<Season> seasonEntities = seasonRepository.findAll();
        Set<Season> seasons = new HashSet<>();
        seasonEntities.forEach(seasons::add);
        return seasons;
    }

    @Override
    public Season currentSeason() {
        return seasonRepository.currentSeason().orElseThrow();
    }
}
