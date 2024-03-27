package com.ofrancome.petanque.domain.players;

import java.util.Set;

public interface PlayerService {

    Player addPlayer(String name);
    Set<Player> retrievePlayers();
}
