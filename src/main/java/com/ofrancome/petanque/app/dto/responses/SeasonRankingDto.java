package com.ofrancome.petanque.app.dto.responses;

import com.ofrancome.petanque.domain.players.Ranking;

import java.io.Serializable;

public record SeasonRankingDto(String player, Integer elo) implements Serializable {

    public static SeasonRankingDto from(Ranking ranking) {
        return new SeasonRankingDto(ranking.getPlayer().getName(), ranking.getElo());
    }
}
