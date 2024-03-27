package com.ofrancome.petanque.domain;

import com.ofrancome.petanque.domain.players.Player;
import com.ofrancome.petanque.domain.players.Ranking;
import com.ofrancome.petanque.domain.seasons.Season;

public class TestUtils {

    public static Player createPlayer(String name, Integer elo) {
        Player player = new Player();
        player.setId(1L);
        player.setName(name);
        Ranking ranking = new Ranking();
        ranking.setId(1L);
        Season season = new Season();
        season.setId(1L);
        ranking.setElo(elo);
        season.addRanking(ranking);
        player.addRanking(ranking);

        return player;
    }


}
