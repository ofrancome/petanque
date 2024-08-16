package com.ofrancome.petanque.app.dto.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public class GameCreationDto {
    @NotEmpty
    private Set<@NotBlank String> winners;

    @NotEmpty
    private Set<String> losers;

    @Max(12)
    private Integer losersScore;

    public GameCreationDto(Set<@NotBlank String> winners, Set<String> losers, Integer losersScore) {
        this.winners = winners;
        this.losers = losers;
        this.losersScore = losersScore;
    }

    public Set<String> getWinners() {
        return winners;
    }

    public void setWinners(Set<String> winners) {
        this.winners = winners;
    }

    public Set<String> getLosers() {
        return losers;
    }

    public void setLosers(Set<String> losers) {
        this.losers = losers;
    }

    public Integer getLosersScore() {
        return losersScore;
    }

    public void setLosersScore(Integer losersScore) {
        this.losersScore = losersScore;
    }
}
