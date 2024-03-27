package com.ofrancome.petanque.domain.games;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface GameService {

    Game addGame(Set<String> winners, Set<String> losers, Integer losersScore);
    Set<Game> retrieveGames();
}
