package com.ofrancome.petanque.app.dto.responses;

import com.ofrancome.petanque.domain.games.WinRateCalculator;
import com.ofrancome.petanque.domain.players.Player;

import java.io.Serializable;
import java.util.List;

public record PlayerDto(String name, String avatar, PlayerRankingDto lastRanking, List<PlayerRankingDto> rankings, Integer nbGames,
                        Integer nbWins, Integer winRate, Long lastSeasonPlayed, Integer nbGamesSeason, Integer nbWinsSeason, Integer winRateSeason)
        implements Serializable {

    public static PlayerDto from(Player player) {
        Long lastSeasonId = player.lastRanking().getSeason().getId();
        Integer nbWinsSeason = player.getGamesWon().stream().filter(game -> game.getSeason().getId().equals(lastSeasonId)).toList().size();
        Integer nbLossSeason = player.getGamesLost().stream().filter(game -> game.getSeason().getId().equals(lastSeasonId)).toList().size();
        int nbGamesSeason = nbWinsSeason + nbLossSeason;
        return new PlayerDto(
                player.getName(),
                player.getAvatar(),
                PlayerRankingDto.from(player.lastRanking()),
                player.getRankings().stream().map(PlayerRankingDto::from).toList(),
                player.getGamesLost().size() + player.getGamesWon().size(),
                player.getGamesWon().size(),
                player.getWinrate(),
                lastSeasonId,
                nbGamesSeason,
                nbWinsSeason,
                WinRateCalculator.calc(Double.valueOf(nbWinsSeason), (double) nbGamesSeason)
        );
    }
}
