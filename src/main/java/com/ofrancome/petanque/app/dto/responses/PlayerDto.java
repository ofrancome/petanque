package com.ofrancome.petanque.app.dto.responses;

import com.ofrancome.petanque.domain.players.Player;

import java.io.Serializable;
import java.util.List;

public record PlayerDto(String name, String avatar, PlayerRankingDto lastRanking, List<PlayerRankingDto> rankings, Integer nbGames, Integer nbWins, Integer winRate)
        implements Serializable {

    public static PlayerDto from(Player player) {
        return new PlayerDto(
                player.getName(),
                player.getAvatar(),
                PlayerRankingDto.from(player.lastRanking()),
                player.getRankings().stream().map(PlayerRankingDto::from).toList(),
                player.getGamesLost().size() + player.getGamesWon().size(),
                player.getGamesWon().size(),
                player.getWinrate()
        );
    }
}
