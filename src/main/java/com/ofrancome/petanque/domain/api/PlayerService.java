package com.ofrancome.petanque.domain.api;

import com.ofrancome.petanque.domain.games.Game;
import com.ofrancome.petanque.domain.players.Player;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PlayerService {

    Player addPlayer(String name);
    Set<Player> retrievePlayers();
    Optional<Player> getPlayer(String name);
    Optional<List<Game>> getAllGamesFor(String name);
}
