package com.ofrancome.petanque.domain.games;

public class WinRateCalculator {

    public static int calc(Double nbWins, Double nbGames) {
        double preciseWinrate = nbWins / nbGames * 100;
        return (int) preciseWinrate;
    }
}
