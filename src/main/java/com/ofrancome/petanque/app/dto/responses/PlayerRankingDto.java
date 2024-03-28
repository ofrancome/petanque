package com.ofrancome.petanque.app.dto.responses;

import com.ofrancome.petanque.domain.players.Ranking;

import java.io.Serializable;

public record PlayerRankingDto(Long season, Integer elo) implements Serializable {

    public static PlayerRankingDto from(Ranking ranking) {
        return new PlayerRankingDto(
                ranking.getSeason().getId(),
                ranking.getElo()
        );
    }
}
