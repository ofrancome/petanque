package com.ofrancome.petanque.domain.elo;

import com.ofrancome.petanque.domain.players.Player;
import com.ofrancome.petanque.domain.players.PlayerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class EloCalculator {

    public static final int K = 32;

    public Integer getEloSwitch(Set<Player> winners, Set<Player> losers) {
        int winningElo = winners.stream().mapToInt(Player::currentElo).sum() / winners.size();
        int losingElo = losers.stream().mapToInt(Player::currentElo).sum() / winners.size();
        double exponent = (double) (losingElo - winningElo) / 400;
        double expectedOutcome = (1 / (1 + (Math.pow(10, exponent))));
        return (int) Math.round(EloCalculator.K * (1 - expectedOutcome));
    }

}
