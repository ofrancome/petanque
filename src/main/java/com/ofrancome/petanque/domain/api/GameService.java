package com.ofrancome.petanque.domain.api;

import com.ofrancome.petanque.domain.games.Game;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface GameService {

    Game addGame(Set<String> winners, Set<String> losers, Integer losersScore);
    Set<Game> retrieveGames();
    void deleteLastGame();
}
