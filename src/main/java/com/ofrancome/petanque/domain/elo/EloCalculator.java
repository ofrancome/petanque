package com.ofrancome.petanque.domain.elo;

import com.ofrancome.petanque.domain.players.Player;
import com.ofrancome.petanque.domain.players.Ranking;
import com.ofrancome.petanque.domain.seasons.Season;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EloCalculator {

    public static final int K = 32;
    public static final int STARTING_ELO = 1200;

    public Integer getEloSwitch(Set<Player> winners, Set<Player> losers, Season currentSeason) {
        int winningElo = averageElo(winners, currentSeason);
        int losingElo = averageElo(losers, currentSeason);
        double exponent = (double) (losingElo - winningElo) / 400;
        double expectedOutcome = (1 / (1 + (Math.pow(10, exponent))));
        return (int) Math.round(EloCalculator.K * (1 - expectedOutcome));
    }

    private static int averageElo(Set<Player> winners, Season currentSeason) {
        int sum = 0;
        for (Player winner : winners) {
            Ranking currentElo = winner.lastRanking();
            sum += currentElo.getSeason().equals(currentSeason) ? currentElo.getElo() : STARTING_ELO;
        }
        return sum / winners.size();
    }

}
