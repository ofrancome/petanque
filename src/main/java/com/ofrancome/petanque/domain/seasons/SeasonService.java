package com.ofrancome.petanque.domain.seasons;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface SeasonService {

    Season newSeason();
    Set<Season> retrieveSeasons();

    Season currentSeason();
}
