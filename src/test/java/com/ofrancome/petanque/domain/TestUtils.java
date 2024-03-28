package com.ofrancome.petanque.domain;

import com.ofrancome.petanque.domain.players.Player;
import com.ofrancome.petanque.domain.players.Ranking;
import com.ofrancome.petanque.domain.seasons.Season;

import java.time.LocalDate;
import java.time.Month;

public class TestUtils {

    public static Player createPlayer(String name, Integer elo) {
        Player player = new Player();
        player.setId(1L);
        player.setName(name);
        Ranking ranking = new Ranking();
        ranking.setId(1L);
        Season season = new Season();
        season.setId(1L);
        season.setStart(LocalDate.of(2024, Month.APRIL, 25));
        ranking.setElo(elo);
        season.addRanking(ranking);
        player.addRanking(ranking);

        return player;
    }


}
