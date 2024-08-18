package com.ofrancome.petanque.app.dto.responses;

import com.ofrancome.petanque.domain.games.Game;
import com.ofrancome.petanque.domain.players.Player;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.List;


public record GameDto(Long gameNumber, List<String> winners, List<String> losers, Integer losersScore, Integer eloSwitch, Long season, String gameDay)
        implements Serializable {

    public static GameDto from(Game game) {
        return new GameDto(
                game.getId(),
                game.getWinners().stream().map(Player::getName).toList(),
                game.getLosers().stream().map(Player::getName).toList(),
                game.getLosersScore(),
                game.getEloSwitch(),
                game.getSeason().getId(),
                game.getGameDay().format(DateTimeFormatter.ISO_LOCAL_DATE)
        );
    }
}
